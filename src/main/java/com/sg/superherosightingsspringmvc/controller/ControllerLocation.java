package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.view.LocationCommandModelCreateEdit;
import com.sg.superherosightingsspringmvc.model.Address;
import com.sg.superherosightingsspringmvc.model.Location;
import com.sg.superherosightingsspringmvc.service.AddressService;
import com.sg.superherosightingsspringmvc.service.LocationService;
import com.sg.superherosightingsspringmvc.service.PersonLocationService;
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
@RequestMapping(value="/Locations") // Sets the base folder this controller works from.
public class ControllerLocation {
    LocationService locationService;
    AddressService addressService;
    PersonLocationService personLocationService;
    
    
    @Inject
    public ControllerLocation(LocationService locationService, AddressService addressService, PersonLocationService personLocationService){
        this.locationService = locationService;
        this.addressService = addressService;
        this.personLocationService = personLocationService;
    }
    
    @RequestMapping(value="/LocationsPage", method=RequestMethod.GET)
    public String LocationsPage(Model model){
        LocationCommandModelCreateEdit commandModel = new LocationCommandModelCreateEdit();
        model.addAttribute("commandModel", buildLists(commandModel));
        return "Locations/list";
    }
    
    @RequestMapping(value="/createLocation", method = RequestMethod.POST)
    public String createPerson(@Valid @ModelAttribute("commandModel") LocationCommandModelCreateEdit commandModel, BindingResult result, HttpServletRequest request, Model model){
        if(result.hasErrors()){
            commandModel = buildLists(commandModel);
            return "Locations/list";
        }
        
        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setAddressCity(request.getParameter("addressCity"));
        address.setAddressState(request.getParameter("addressState"));
        address.setAddressZip(request.getParameter("addressZip"));
        address.setAddressCountry(request.getParameter("addressCountry"));
        
        Location location = new Location();
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setLocationLatitude(request.getParameter("locationLatitude"));
        location.setLocationLongitude(request.getParameter("locationLongitude"));
        
        Address createAddress = addressService.create(address);
        location.setLocationAddressId(createAddress.getAddressId());
        locationService.create(location);
        
        return "redirect:LocationsPage";
    }
    
    @RequestMapping(value="/locationDetails", method = RequestMethod.GET)
    public String locationDetails(HttpServletRequest request, Model model){
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        
        Location location = locationService.read(locationId);
        model.addAttribute("location", location);
        
        Address address = addressService.read(location.getLocationAddressId());
        model.addAttribute("address", address);
        
        return "Locations/detail";
    }
    
    @RequestMapping(value="/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request){
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationService.read(locationId);
        
//        // Have to delete all references from the PersonLocation bridge table before you can delete the Location from the Database
//        for(PersonLocation currentPL : personLocationService.getAllPersonLocations()){
//            if(currentPL.getLocation().equals(location)){
//                personLocationService.delete(currentPL);
//            }
//        }
        
        // Should be able to delete the location now.
        locationService.delete(location);
        return "redirect:LocationsPage";
    }
    
    @RequestMapping(value="/displayEditLocationPage", method = RequestMethod.GET)
    public String displayEditLocationPage(HttpServletRequest request, Model model){
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationService.read(locationId);
        Address address = addressService.read(location.getLocationAddressId());

        LocationCommandModelCreateEdit commandModel = new LocationCommandModelCreateEdit();
        commandModel.setLocationId(locationId);
        commandModel.setLocationName(location.getLocationName());
        commandModel.setLocationDescription(location.getLocationDescription());
        commandModel.setLocationAddressId(location.getLocationAddressId());
        commandModel.setLocationLatitude(location.getLocationLatitude());
        commandModel.setLocationLongitude(location.getLocationLongitude());
        
        commandModel.setAddressId(location.getLocationAddressId());
        commandModel.setAddressLine1(address.getAddressLine1());
        commandModel.setAddressLine2(address.getAddressLine2());
        commandModel.setAddressCity(address.getAddressCity());
        commandModel.setAddressState(address.getAddressState());
        commandModel.setAddressZip(address.getAddressZip());
        commandModel.setAddressCountry(address.getAddressCountry());
        
        model.addAttribute("commandModel", commandModel);
        return "Locations/edit";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("commandModel") LocationCommandModelCreateEdit commandModel, BindingResult result){
        if(result.hasErrors()){
            return "Locations/edit";
        }
        Location location = new Location();
        Address address = new Address();
        
        location.setLocationId(commandModel.getLocationId());
        location.setLocationName(commandModel.getLocationName());
        location.setLocationDescription(commandModel.getLocationDescription());
        location.setLocationAddressId(commandModel.getLocationAddressId());
        location.setLocationLatitude(commandModel.getLocationLatitude());
        location.setLocationLongitude(commandModel.getLocationLongitude());
        
        address.setAddressId(commandModel.getAddressId());
        address.setAddressLine1(commandModel.getAddressLine1());
        address.setAddressLine2(commandModel.getAddressLine2());
        address.setAddressCity(commandModel.getAddressCity());
        address.setAddressState(commandModel.getAddressState());
        address.setAddressZip(commandModel.getAddressZip());
        address.setAddressCountry(commandModel.getAddressCountry());
        
        locationService.update(location);
        addressService.update(address);
        return "redirect:LocationsPage";
    }
    
    private LocationCommandModelCreateEdit buildLists(LocationCommandModelCreateEdit commandModel){
        List<Location> locationList = locationService.getAllLocations();
        commandModel.setLocationList(locationList);
        return commandModel;
    }
}
