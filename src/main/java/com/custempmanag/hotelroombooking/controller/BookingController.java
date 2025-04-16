package com.custempmanag.hotelroombooking.controller;

import com.custempmanag.hotelroombooking.DTO.BookingDTO;
import com.custempmanag.hotelroombooking.DTO.BookingRequestDTO;
import com.custempmanag.hotelroombooking.response.MessageResponse;
import com.custempmanag.hotelroombooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping()
    public ResponseEntity<?> getBooking() {
        MessageResponse messageResponse = bookingService.getBookings();
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable Long id) {
        MessageResponse messageResponse = bookingService.getBookingById(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
        MessageResponse messageResponse = bookingService.createBooking(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageResponse);
    }
    // PUT /bookings/{id}/cancel

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelBooking(@PathVariable Long id) {
        MessageResponse messageResponse = bookingService.cancelBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }
    // GET /bookings/{id}
}

