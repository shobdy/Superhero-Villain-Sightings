package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.view.LocationCommandModelCreateEdit;
import com.sg.superherosightingsspringmvc.view.PersonLocationCommandModelCreateEdit;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.model.Organization;
import com.sg.superherosightingsspringmvc.model.Person;
import com.sg.superherosightingsspringmvc.model.PersonLocation;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.service.PersonLocationService;
import com.sg.superherosightingsspringmvc.service.PersonService;
import com.sg.superherosightingsspringmvc.view.PersonCommandModelCreateEdit;
import java.time.LocalDate;
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
@RequestMapping(value = "/Sightings")
public class ControllerSighting {

    PersonLocationService personLocationService;
    PersonService personService;
    LocationService locationService;

    @Inject
    public ControllerSighting(PersonLocationService personLocationService, PersonService personService, LocationService locationService) {
        this.personLocationService = personLocationService;
        this.personService = personService;
        this.locationService = locationService;
    }

    @RequestMapping(value = "/SightingsPage", method = RequestMethod.GET)
    public String SightingsPage(Model model) {
        PersonLocationCommandModelCreateEdit commandModel = new PersonLocationCommandModelCreateEdit();
        model.addAttribute("commandModel", buildLists(commandModel));
        return "Sightings/list";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(@Valid @ModelAttribute("commandModel") PersonLocationCommandModelCreateEdit commandModel, BindingResult result, HttpServletRequest request) {
        if(result.hasErrors()){
            commandModel = buildLists(commandModel);
            
            return "Sightings/list";
        }
        
        PersonLocation pl = new PersonLocation();

        pl.setDateSighted(LocalDate.parse(request.getParameter("dateSighted")));
        pl.setPerson(personService.read(Integer.parseInt(request.getParameter("personId"))));
        pl.setLocation(locationService.read(Integer.parseInt(request.getParameter("locationId"))));
        personLocationService.create(pl);

        return "redirect:SightingsPage";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        int personLocationId = Integer.parseInt(request.getParameter("personLocationId"));
        PersonLocation pl = personLocationService.read(personLocationId);
        personLocationService.delete(pl);
        return "redirect:SightingsPage";
    }

    @RequestMapping(value = "/displayEditSightingPage", method = RequestMethod.GET)
    public String displayEditSightingPage(HttpServletRequest request, Model model) {
        PersonLocationCommandModelCreateEdit commandModel = new PersonLocationCommandModelCreateEdit();
        // Sending a list of all Persons and Locations through commandModel
        List<Person> personList = personService.getAllPersons();
        List<Location> locationList = locationService.getAllLocations();
        commandModel.setPersonList(personList);
        commandModel.setLocationList(locationList);
        
        // Sending the individual personLocation Info through commandModel
        PersonLocation pl = personLocationService.read(Integer.parseInt(request.getParameter("personLocationId")));
        commandModel.setPersonLocationId(pl.getPersonLocationId());
        commandModel.setPersonId(pl.getPerson().getPersonId());
        commandModel.setLocationId(pl.getLocation().getLocationId());
        commandModel.setDateSighted(pl.getDateSighted());
        
        model.addAttribute("commandModel", commandModel);
        return "Sightings/edit";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(@Valid @ModelAttribute("commandModel") PersonLocationCommandModelCreateEdit commandModel, BindingResult result) {
        if (result.hasErrors()) {
            // Adding the next line ensures you are passing back the lists needed to recreate the drop down options and selected items.
            commandModel = buildLists(commandModel);
            return "Sightings/edit";
        }
        
        Person person = personService.read(commandModel.getPersonId());
        Location location = locationService.read(commandModel.getLocationId());
        
        //Mapping the data from commandModel back to a PersonLocation object
        PersonLocation pl = personLocationService.read(commandModel.getPersonLocationId());
        pl.setPerson(person);
        pl.setLocation(location);
        pl.setDateSighted(commandModel.getDateSighted());
        
        personLocationService.update(pl);

        return "redirect:SightingsPage";
    }
    
    private PersonLocationCommandModelCreateEdit buildLists(PersonLocationCommandModelCreateEdit commandModel){
                List<PersonLocation> personLocationList = personLocationService.getAllPersonLocations();
                List<Person> personList = personService.getAllPersons();
                List<Location> locationList = locationService.getAllLocations();
                commandModel.setPersonList(personList);
                commandModel.setLocationList(locationList);
                commandModel.setPersonLocationList(personLocationList);
                return commandModel;
    }

}
