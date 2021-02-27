package br.com.leonino.saladereuniao.controller;

import br.com.leonino.saladereuniao.exception.ResourceNotFoundException;
import br.com.leonino.saladereuniao.model.Room;
import br.com.leonino.saladereuniao.repository.RoomRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found:: "+ id));

        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room, BindingResult result) {

        Room roomSaved = roomRepository.save(room);
        return ResponseEntity.ok().body(roomSaved);
    }

}
