package com.custempmanag.hotelroombooking.DTO;

import com.custempmanag.hotelroombooking.model.Room;

import java.math.BigDecimal;

public class RoomDTO {
    private Long id;
    private String roomNumber;
    private Integer capacity;
    private BigDecimal pricePerNight;
    private boolean available;

    public RoomDTO() {
    }

    public RoomDTO(String roomNumber, Integer capacity, BigDecimal pricePerNight, boolean available) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public RoomDTO(Long id, String roomNumber, Integer capacity, BigDecimal pricePerNight, boolean available) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.pricePerNight = pricePerNight;
        this.available = available;
    }

    public RoomDTO(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.capacity = room.getCapacity();
        this.pricePerNight = room.getPricePerNight();
        this.available = room.isAvailable();

    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(BigDecimal pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

