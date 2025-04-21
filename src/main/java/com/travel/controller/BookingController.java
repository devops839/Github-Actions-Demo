package com.travel.controller;

import com.travel.model.Booking;
import com.travel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book")
    public String createBooking(@RequestParam String customerName,
                                @RequestParam String destinationName,
                                @RequestParam String bookingDate,
                                @RequestParam Double totalAmount) {
        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setDestinationName(destinationName);
        booking.setBookingDate(bookingDate);
        booking.setTotalAmount(totalAmount);
        bookingService.createBooking(booking);
        return "bookingSuccess";  // Redirect to a success page after booking
    }
}
