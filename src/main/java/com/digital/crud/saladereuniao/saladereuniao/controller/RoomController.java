package com.digital.crud.saladereuniao.saladereuniao.controller;

import com.digital.crud.saladereuniao.saladereuniao.exception.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.model.Room;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public ResponseEntity<List<Room>> getRooms(){
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable("id") Long roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        return ResponseEntity.ok().body(room);
    }

    @CrossOrigin("http://localhost:4200")
    @PostMapping()
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room){
        return ResponseEntity.ok(roomRepository.save(room));
    }

    @CrossOrigin("http://localhost:4200")
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Long roomId, @Valid @RequestBody Room room) throws ResourceNotFoundException {
        Room toUpdate = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        toUpdate.setName(room.getName());
        toUpdate.setDate(room.getDate());
        toUpdate.setStartHour(room.getStartHour());
        toUpdate.setEndHour(room.getEndHour());
        return ResponseEntity.ok(roomRepository.save(toUpdate));
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable("id") Long roomId) throws ResourceNotFoundException {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Room not found" + roomId));
        roomRepository.delete(room);
        return ResponseEntity.ok("success");
    }
}
