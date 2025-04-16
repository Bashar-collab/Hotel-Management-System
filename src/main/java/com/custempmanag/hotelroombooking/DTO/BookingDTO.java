package com.custempmanag.hotelroombooking.DTO;

import com.custempmanag.hotelroombooking.model.Booking;
import com.custempmanag.hotelroombooking.model.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDTO {
    private Long id;
    private String customerName;
    private RoomDTO room;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus status;

    public BookingDTO() {
    }

    public BookingDTO(RoomDTO room, String customerName, LocalDate checkIn, LocalDate checkOut, BookingStatus status) {
        this.room = room;
        this.customerName = customerName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public BookingDTO(Long id, String customerName, RoomDTO room, LocalDate checkIn, LocalDate checkOut, BookingStatus status) {
        this.id = id;
        this.customerName = customerName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.room = new RoomDTO(booking.getRoom());
        this.checkIn = booking.getCheckIn();
        this.checkOut = booking.getCheckOut();
        this.status = booking.getStatus();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
