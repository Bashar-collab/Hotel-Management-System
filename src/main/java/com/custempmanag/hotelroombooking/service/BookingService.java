package com.custempmanag.hotelroombooking.service;

import com.custempmanag.hotelroombooking.DTO.BookingDTO;
import com.custempmanag.hotelroombooking.DTO.BookingRequestDTO;
import com.custempmanag.hotelroombooking.DTO.RoomDTO;
import com.custempmanag.hotelroombooking.exception.CustomException;
import com.custempmanag.hotelroombooking.model.Booking;
import com.custempmanag.hotelroombooking.model.BookingStatus;
import com.custempmanag.hotelroombooking.model.Room;
import com.custempmanag.hotelroombooking.repository.BookingRepository;
import com.custempmanag.hotelroombooking.repository.RoomRepository;
import com.custempmanag.hotelroombooking.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public MessageResponse getBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        if(bookings.isEmpty()){
            return new MessageResponse(HttpStatus.NOT_FOUND.toString(), "No bookings found!", null);
        }

        List<BookingDTO> bookingDTOS = bookings.stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());

        return new MessageResponse(HttpStatus.OK.toString(), "Bookings retrieved successfully!", bookingDTOS);
    }

    public MessageResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found"));
        BookingDTO bookingDTO = convertToDTO(booking);
        return new MessageResponse(HttpStatus.OK.toString(), "Booking retrieved successfully!", bookingDTO);
    }
    // Create a booking and validate availability

    @Transactional
    public MessageResponse createBooking(BookingRequestDTO request) {
        // Check if the room exists
        Room room = roomRepository.findById(request.getRoomNumber())
                .orElseThrow(() -> new CustomException("Room not found"));

        // Check for overlapping bookings
        List<Booking> conflicts = bookingRepository.findOverlappingBookings(
                room.getId(), request.getCheckIn(), request.getCheckOut());

        if (!conflicts.isEmpty()) {
            throw new CustomException("Room is already booked for the selected time.");
        }

        // Create the booking
        Booking booking = new Booking();
        booking.setCustomerName(request.getCustomerName());
        booking.setRoom(room);
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setStatus(BookingStatus.valueOf("CONFIRMED"));

        // Save the booking and mark room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

        booking = bookingRepository.save(booking);

        logger.info("📧 Email confirmation sent to user: Booking #{} confirmed for Room {} from {} to {}",
                booking.getCustomerName(), room.getRoomNumber(), booking.getCheckIn(), booking.getCheckOut());
        BookingDTO bookingDTO = convertToDTO(booking);
        return new MessageResponse(HttpStatus.CREATED.toString(), "Booking created successfully!", bookingDTO);
    }
    // Cancel a booking

    public MessageResponse cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new CustomException("Booking not found"));

        // Mark the room as available again
        Room room = booking.getRoom();
        room.setAvailable(true);
        roomRepository.save(room);

        logger.info("📧 Email confirmation sent to user: Booking #{} confirmed for Room {}",
                booking.getCustomerName(), room.getRoomNumber());
        // Update the booking status
        booking.setStatus(BookingStatus.valueOf("CANCELLED"));
        bookingRepository.save(booking);
        return new MessageResponse(HttpStatus.OK.toString(), "Booking cancelled successfully!", null);
    }
    // Get booking details

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setRoom(new RoomDTO(booking.getRoom().getId(), booking.getRoom().getRoomNumber(), booking.getRoom().getCapacity(), booking.getRoom().getPricePerNight(), booking.getRoom().isAvailable()));
        dto.setCheckIn(booking.getCheckIn());
        dto.setCheckOut(booking.getCheckOut());
        dto.setStatus(booking.getStatus());
        return dto;
    }
}
