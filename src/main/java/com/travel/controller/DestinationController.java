package com.travel.controller;

import com.travel.model.Destination;
import com.travel.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DestinationController {

    @Autowired
    private DestinationService destinationService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("destinations", destinationService.getAllDestinations());
        return "index";
    }

    @GetMapping("/destination/{id}")
    public String destinationDetails(@PathVariable Long id, Model model) {
        model.addAttribute("destination", destinationService.getDestinationById(id));
        return "destination";
    }

    @PostMapping("/destination")
    public String addDestination(@ModelAttribute Destination destination) {
        destinationService.saveDestination(destination);
        return "redirect:/";
    }
}