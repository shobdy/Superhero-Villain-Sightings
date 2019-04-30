package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.model.PersonLocation;
import com.sg.superherosightingsspringmvc.service.PersonLocationService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {

    PersonLocationService personLocationService;

    @Inject
    public IndexController(PersonLocationService personLocationService) {
        this.personLocationService = personLocationService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<PersonLocation> lastTen = personLocationService.getLastTenSightings();
        model.addAttribute("lastTen", lastTen);
        return "index";
    }
    
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(Model model) {
        List<PersonLocation> lastTen = personLocationService.getLastTenSightings();
        model.addAttribute("lastTen", lastTen);
        return "home";
    }
}
