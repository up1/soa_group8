package com.tytle.kmitlresidence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tytle.kmitlresidence.model.RoomType;
import com.tytle.kmitlresidence.repository.RoomTypeRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class RoomTypeController {

    private RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeController(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    // ----------------------------- Retrieve All Room Types -----------------------------
    @GetMapping("/all-room-type")
    public List<RoomType> getAllRoomType() {
        return this.roomTypeRepository.getRoomTypes();
    }

    // ----------------------------- Retrieve Room Type -----------------------------
    @GetMapping("/room-type")
    public RoomType getRoomType(@RequestParam(value = "id", defaultValue = "1") int id) {
        return this.roomTypeRepository.getRoomTypeByID(id);
    }
}