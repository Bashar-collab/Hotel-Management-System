package com.custempmanag.hotelroombooking.controller;

import com.custempmanag.hotelroombooking.DTO.RoomDTO;
import com.custempmanag.hotelroombooking.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@Tag(name = "Rooms", description = "Endpoints for room management")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // GET /rooms?available=true
    @GetMapping
    @Operation(summary = "Get list of rooms", description = "Optional filter by availability")
    public ResponseEntity<?> getRooms(@RequestParam(required = false) Boolean available) {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getRooms(available));
    }

    // POST /rooms
    @PostMapping
    @Operation(summary = "Create a new room")
    public ResponseEntity<?> addRoom(@RequestBody RoomDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roomService.addRoom(dto));
    }
}
