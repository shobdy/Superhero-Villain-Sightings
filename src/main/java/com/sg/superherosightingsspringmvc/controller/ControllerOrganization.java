package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.view.OrganizationCommandModelCreateEdit;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.PersonOrganization;
import com.sg.superherosightingsspringmvc.service.AddressService;
import com.sg.superherosightingsspringmvc.service.OrganizationService;
import com.sg.superherosightingsspringmvc.service.PersonOrganizationService;
import com.sg.superherosightingsspringmvc.view.PersonCommandModelCreateEdit;
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
@RequestMapping(value="/Organizations")
public class ControllerOrganization {
    OrganizationService organizationService;
    AddressService addressService;
    PersonOrganizationService personOrganizationService;
    
    @Inject
    public ControllerOrganization(OrganizationService organizationService, AddressService addressService, PersonOrganizationService personOrganizationService){
        this.organizationService = organizationService;
        this.addressService = addressService;
        this.personOrganizationService = personOrganizationService;
    }
    
    @RequestMapping(value="/OrganizationsPage", method=RequestMethod.GET)
    public String OrganizationsPage(Model model){
        OrganizationCommandModelCreateEdit commandModel = new OrganizationCommandModelCreateEdit();
        model.addAttribute("commandModel", buildLists(commandModel));
        return "Organizations/list";
    }
    
    @RequestMapping(value="/createOrganization", method = RequestMethod.POST)
    public String createOrganization(@Valid @ModelAttribute("commandModel") OrganizationCommandModelCreateEdit commandModel, BindingResult result, HttpServletRequest request, Model model){
        if(result.hasErrors()){
            commandModel = buildLists(commandModel);
            
            return "Organizations/list";
        }
        
        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setAddressCity(request.getParameter("addressCity"));
        address.setAddressState(request.getParameter("addressState"));
        address.setAddressZip(request.getParameter("addressZip"));
        address.setAddressCountry(request.getParameter("addressCountry"));
        
        Organization org = new Organization();
        org.setOrganizationName(request.getParameter("organizationName"));
        org.setOrganizationDescription(request.getParameter("organizationDescription"));
        org.setOrganizationContactName(request.getParameter("organizationContactName"));
        org.setOrganizationContactPhone(request.getParameter("organizationContactPhone"));
        org.setOrganizationContactEmail(request.getParameter("organizationContactEmail"));
        
        Address createAddress = addressService.create(address);
        org.setOrganizationAddressId(createAddress.getAddressId());
        organizationService.create(org);
        
        return "redirect:OrganizationsPage";
    }
    
    @RequestMapping(value="/organizationDetails", method = RequestMethod.GET)
    public String organizationDetails(HttpServletRequest request, Model model){
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        
        Organization organization = organizationService.read(organizationId);
        model.addAttribute("organization", organization);
        
        Address address = addressService.read(organization.getOrganizationAddressId());
        model.addAttribute("address", address);
        
        return "Organizations/detail";
    }
    
    @RequestMapping(value="/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request){
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        Organization organization = organizationService.read(organizationId);
        
        // Have to delete all references from the PersonOrganization bridge table before you can delete the Organization from the Database
        for(PersonOrganization currentPO : personOrganizationService.getAllPersonOrganizations()){
            if(currentPO.getOrganization().equals(organization)){
                personOrganizationService.delete(currentPO);
            }
        }
        
        // Should be able to delete organization now that all the PersonOrganization records have been removed.
        organizationService.delete(organization);
        return "redirect:OrganizationsPage";
    }
    
    @RequestMapping(value="/displayEditOrganizationPage", method = RequestMethod.GET)
    public String displayEditOrganizationPage(HttpServletRequest request, Model model){
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        Organization organization = organizationService.read(organizationId);
        Address address = addressService.read(organization.getOrganizationAddressId());

        OrganizationCommandModelCreateEdit commandModel = new OrganizationCommandModelCreateEdit();
        commandModel.setOrganizationId(organizationId);
        commandModel.setOrganizationName(organization.getOrganizationName());
        commandModel.setOrganizationDescription(organization.getOrganizationDescription());
        commandModel.setOrganizationAddressId(organization.getOrganizationAddressId());
        commandModel.setOrganizationContactName(organization.getOrganizationContactName());
        commandModel.setOrganizationContactPhone(organization.getOrganizationContactPhone());
        commandModel.setOrganizationContactEmail(organization.getOrganizationContactEmail());
        
        commandModel.setAddressId(organization.getOrganizationAddressId());
        commandModel.setAddressLine1(address.getAddressLine1());
        commandModel.setAddressLine2(address.getAddressLine2());
        commandModel.setAddressCity(address.getAddressCity());
        commandModel.setAddressState(address.getAddressState());
        commandModel.setAddressZip(address.getAddressZip());
        commandModel.setAddressCountry(address.getAddressCountry());
        
        model.addAttribute("commandModel", commandModel);
        return "Organizations/edit";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("commandModel") OrganizationCommandModelCreateEdit commandModel, BindingResult result){
        if(result.hasErrors()){
            return "Organizations/edit";
        }
        Organization organization = new Organization();
        Address address = new Address();
        
        organization.setOrganizationId(commandModel.getOrganizationId());
        organization.setOrganizationName(commandModel.getOrganizationName());
        organization.setOrganizationDescription(commandModel.getOrganizationDescription());
        organization.setOrganizationAddressId(commandModel.getOrganizationAddressId());
        organization.setOrganizationContactName(commandModel.getOrganizationContactName());
        organization.setOrganizationContactPhone(commandModel.getOrganizationContactPhone());
        organization.setOrganizationContactEmail(commandModel.getOrganizationContactEmail());
        
        address.setAddressId(commandModel.getAddressId());
        address.setAddressLine1(commandModel.getAddressLine1());
        address.setAddressLine2(commandModel.getAddressLine2());
        address.setAddressCity(commandModel.getAddressCity());
        address.setAddressState(commandModel.getAddressState());
        address.setAddressZip(commandModel.getAddressZip());
        address.setAddressCountry(commandModel.getAddressCountry());
        
        organizationService.update(organization);
        addressService.update(address);
        return "redirect:OrganizationsPage";
    }

    private OrganizationCommandModelCreateEdit buildLists(OrganizationCommandModelCreateEdit commandModel){
        List<Organization> organizationList = organizationService.getAllOrganizations();
        commandModel.setOrganizationList(organizationList);
        return commandModel;
    }
}
