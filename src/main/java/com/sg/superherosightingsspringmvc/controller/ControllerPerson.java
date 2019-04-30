package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.view.PersonCommandModelCreateEdit;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
import com.sg.superherosightingsspringmvc.service.OrganizationService;
import com.sg.superherosightingsspringmvc.service.PersonLocationService;
import com.sg.superherosightingsspringmvc.service.PersonOrganizationService;
import com.sg.superherosightingsspringmvc.service.PersonService;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/Person")
public class ControllerPerson {
    
    PersonService personService;
    OrganizationService organizationService;
    PersonOrganizationService personOrganizationService;
    PersonLocationService personLocationService;
    
    @Inject
    public ControllerPerson(PersonService personService, OrganizationService organizationService, PersonOrganizationService personOrganizationService, PersonLocationService personLocationService){
        this.personService = personService;
        this.organizationService = organizationService;
        this.personOrganizationService = personOrganizationService;
        this.personLocationService = personLocationService;
    }
    
    @RequestMapping(value="/PersonPage", method=RequestMethod.GET)
    public String PersonPage(Model model){
        PersonCommandModelCreateEdit commandModel = new PersonCommandModelCreateEdit();
        model.addAttribute("commandModel", buildLists(commandModel));
        return "Person/list";
    }
    
    @RequestMapping(value="/createPerson", method = RequestMethod.POST)
    public String createPerson(@Valid @ModelAttribute("commandModel") PersonCommandModelCreateEdit commandModel, BindingResult result, HttpServletRequest request, Model model){
        
        if(result.hasErrors()){
            // adding the next line ensures the lists are passed back with selected options.
            commandModel = buildLists(commandModel);
            
            return "Person/list";
        }

        // Creates the person in the database
        Person person = new Person();
        person.setPersonType(request.getParameter("personType"));
        person.setPersonName(request.getParameter("personName"));
        person.setPersonDescription(request.getParameter("personDescription"));
        person.setPersonPower(request.getParameter("personPower"));
        person.setPersonActive(true);
        personService.create(person);
        
        // Factored this block of code to private method, used here and edit method below.
        writePersonOrganization(person, commandModel.getOrganizationIds());

        return "redirect:PersonPage";
    }
    
    @RequestMapping(value="/personDetails", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, Model model){
        PersonCommandModelCreateEdit commandModel = new PersonCommandModelCreateEdit();
        
        Person person = personService.read(Integer.parseInt(request.getParameter("personId")));
        commandModel.setPersonName(person.getPersonName());
        commandModel.setPersonType(person.getPersonType());
        commandModel.setPersonPower(person.getPersonPower());
        commandModel.setPersonDescription(person.getPersonDescription());
        
        // retrieve a list of organizations by person and send to commandModel.
        commandModel.setOrganizationList(organizationService.getOrganizationsByPerson(person));
        model.addAttribute("commandModel", commandModel);
        
        return "Person/detail";
    }
    
    @RequestMapping(value="/deletePerson", method = RequestMethod.GET)
    public String deletePerson(HttpServletRequest request){
        int personId = Integer.parseInt(request.getParameter("personId"));
        Person person = personService.read(personId);
        personService.delete(person);
        return "redirect:PersonPage";
    }
    
    @RequestMapping(value="/displayEditPersonPage", method = RequestMethod.GET)
    public String displayEditPersonPage(HttpServletRequest request, Model model){
        PersonCommandModelCreateEdit commandModel = new PersonCommandModelCreateEdit();
        
        // Add person info to commandModel
        Person person = personService.read(Integer.parseInt(request.getParameter("personId")));
        commandModel.setPersonId(person.getPersonId());
        commandModel.setPersonType(person.getPersonType());
        commandModel.setPersonName(person.getPersonName());
        commandModel.setPersonDescription(person.getPersonDescription());
        commandModel.setPersonPower(person.getPersonPower());
        
        // Create organization list to pass to the drop down as options
        List<Organization> organizationList = organizationService.getAllOrganizations();
        List<PersonOrganization> personOrganizationList = personOrganizationService.getAllPersonOrganizations();
        commandModel.setOrganizationList(organizationList);
        
        // retrieve a list of organization by person
        List<Organization> poList = organizationService.getOrganizationsByPerson(person);
        
        // Create organization ID array and fill up with IDs of current PersonOrganzations person belongs to
        int[] organizationIds = new int[poList.size()];
        int count = 0;
        for(Organization currentPO : poList){
            organizationIds[count] = currentPO.getOrganizationId();
            count++;
        }
        commandModel.setOrganizationIds(organizationIds);

        model.addAttribute("commandModel", commandModel);
        return "Person/edit";
    }
    
    @RequestMapping(value = "/editPerson", method = RequestMethod.POST)
    public String editPerson(@Valid @ModelAttribute("commandModel") PersonCommandModelCreateEdit commandModel, BindingResult result){
        if(result.hasErrors()){
            // adding the next line ensures the lists are passed back with selected options.
            commandModel = buildLists(commandModel);
            return "Person/edit";
        }
        
        // Create person and update database with it
        Person person = new Person();
        person.setPersonId(commandModel.getPersonId());
        person.setPersonType(commandModel.getPersonType());
        person.setPersonName(commandModel.getPersonName());
        person.setPersonDescription(commandModel.getPersonDescription());
        person.setPersonPower(commandModel.getPersonPower());
        personService.update(person);
        
        // Get list of current PersonOrganizations and delete from database
        List<PersonOrganization> existingPOs = personOrganizationService.getAllPersonOrganizationsByPerson(person);
        for(PersonOrganization currentPO : existingPOs){
            personOrganizationService.delete(currentPO);
        }
        
        // Factored this block of code to private method, used here and create method above.
        writePersonOrganization(person, commandModel.getOrganizationIds());
        
        return "redirect:PersonPage";
    }
    
    private PersonCommandModelCreateEdit buildLists(PersonCommandModelCreateEdit commandModel){
        List<Person> personList = personService.getAllPersons();
        List<Organization> organizationList = organizationService.getAllOrganizations();
        commandModel.setPersonList(personList);
        commandModel.setOrganizationList(organizationList);
        
        return commandModel;
    }
    
    private void writePersonOrganization(Person person, int[] orgIds){
            
        // Creates a list of organization objects based on the commandmodels return of organizationIds
        List<Organization> organizationList = new ArrayList<>();
        for(int currentOrgId : orgIds){
            organizationList.add(organizationService.read(currentOrgId));
        }
        
        // Creates every PersonOrganization in the database
        for(Organization currentOrg : organizationList){
            PersonOrganization po = new PersonOrganization();
            po.setOrganization(currentOrg);
            po.setPerson(person);
            personOrganizationService.create(po);
        }
    }
}
