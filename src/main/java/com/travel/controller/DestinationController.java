package com.travel.controller;

import com.travel.model.Destination;
import com.travel.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/destinations")
    public String getDestinations(Model model) {
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "destination";
    }
}
