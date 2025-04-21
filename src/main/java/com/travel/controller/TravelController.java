package com.travel.controller;

import com.travel.model.Booking;
import com.travel.model.Destination;
import com.travel.repository.BookingRepository;
import com.travel.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TravelController {

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Destination> destinations = destinationRepository.findAll();
        model.addAttribute("destinations", destinations);
        return "index";
    }

    @GetMapping("/destination/{id}")
    public String destinationDetails(@PathVariable Long id, Model model) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid destination Id:" + id));
        model.addAttribute("destination", destination);
        return "destination";
    }

    @PostMapping("/book/{id}")
    public String bookDestination(@PathVariable Long id, Authentication authentication) {
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid destination Id:" + id));
        Booking booking = new Booking();
        booking.setUsername(authentication.getName());
        booking.setDestination(destination);
        booking.setBookingDate(LocalDateTime.now().toString());
        bookingRepository.save(booking);
        return "redirect:/bookings";
    }

    @GetMapping("/bookings")
    public String viewBookings(Model model, Authentication authentication) {
        List<Booking> bookings = bookingRepository.findByUsername(authentication.getName());
        model.addAttribute("bookings", bookings);
        return "bookings";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}