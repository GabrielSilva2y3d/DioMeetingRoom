package com.dio.crud.meetingroom.meetingRoom.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dio.crud.meetingroom.meetingRoom.repository.RoomRepository;
import com.dio.crud.meetingroom.meetingRoom.exception.ResourceNotFoundException;
import com.dio.crud.meetingroom.meetingRoom.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {
    
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/id")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") long roomId)
    throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: "+ roomId));
        return ResponseEntity.ok().body(room);

    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/id")
    public ResponseEntity<Room> updateRoom(@Valid @RequestBody @PathVariable(value = "id")
    long roomId, Room roomDetails) 
    throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: "+ roomId));
        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);

    }
    
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
    throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
        .orElseThrow(() -> new ResourceNotFoundException("Room not found: "+ roomId));

        roomRepository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
