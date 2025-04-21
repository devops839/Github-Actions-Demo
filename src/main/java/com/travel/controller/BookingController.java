package com.travel.controller;

import com.travel.model.Booking;
import com.travel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking/{destinationId}")
    public String bookingForm(@PathVariable Long destinationId, Model model) {
        Booking booking = new Booking();
        booking.setDestinationId(destinationId);
        model.addAttribute("booking", booking);
        return "booking";
    }

    @PostMapping("/booking")
    public String createBooking(@ModelAttribute Booking booking) {
        bookingService.saveBooking(booking);
        return "redirect:/";
    }
}