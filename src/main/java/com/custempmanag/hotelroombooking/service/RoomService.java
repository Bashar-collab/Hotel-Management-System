package com.custempmanag.hotelroombooking.service;

import com.custempmanag.hotelroombooking.DTO.RoomDTO;
import com.custempmanag.hotelroombooking.exception.CustomException;
import com.custempmanag.hotelroombooking.model.Room;
import com.custempmanag.hotelroombooking.repository.RoomRepository;
import com.custempmanag.hotelroombooking.response.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Get rooms by availability filter
    public MessageResponse getRooms(Boolean available) {
        List<Room> rooms = (available == null) ? roomRepository.findAll() : roomRepository.findByAvailable(available);
        List<RoomDTO> roomDTOS = rooms.stream().map(RoomDTO::new).toList();
        return new MessageResponse(HttpStatus.OK.toString(), "Rooms retrieved successfully", roomDTOS);
    }

    // Create a new room
    @Transactional
    public MessageResponse addRoom(RoomDTO dto) {
        if(roomRepository.existsByRoomNumber(dto.getRoomNumber()))
            throw new CustomException("A room with same room number already exists");
        Room room = new Room();
        room.setRoomNumber(dto.getRoomNumber());
        room.setCapacity(dto.getCapacity());
        room.setPricePerNight(dto.getPricePerNight());
        room.setAvailable(true);
        room = roomRepository.save(room);
        RoomDTO roomDTO = convertToDTO(room);
        return new MessageResponse(HttpStatus.CREATED.toString(), "Room created successfully", roomDTO);
    }

    private RoomDTO convertToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setCapacity(room.getCapacity());
        dto.setPricePerNight(room.getPricePerNight());
        dto.setAvailable(room.isAvailable());
        return dto;
    }
}
