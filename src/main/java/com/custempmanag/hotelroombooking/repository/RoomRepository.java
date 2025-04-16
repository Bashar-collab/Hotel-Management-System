package com.custempmanag.hotelroombooking.repository;

import com.custempmanag.hotelroombooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByAvailable(Boolean available);

    boolean existsByRoomNumber(String roomNumber);
}

