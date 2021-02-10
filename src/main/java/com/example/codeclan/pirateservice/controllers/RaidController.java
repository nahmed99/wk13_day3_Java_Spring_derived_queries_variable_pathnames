package com.example.codeclan.pirateservice.controllers;

import com.example.codeclan.pirateservice.models.Pirate;
import com.example.codeclan.pirateservice.models.Raid;
import com.example.codeclan.pirateservice.models.Ship;
import com.example.codeclan.pirateservice.repositories.RaidRepository;
import com.example.codeclan.pirateservice.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RaidController {

    @Autowired
    RaidRepository raidRepository;

    @GetMapping(value = "/raids")
    public ResponseEntity<List<Raid>> getAllRaids() {
        List<Raid> allRaids = raidRepository.findAll();
        return new ResponseEntity<>(allRaids, HttpStatus.OK);
    }

    // SHOW (by id)
    @GetMapping(value = "/raids/{id}")
    public ResponseEntity<Optional<Raid>> getRaid(@PathVariable Long id){
        return new ResponseEntity<>(raidRepository.findById(id), HttpStatus.OK);
    }

    // SHOW (by location)
    @GetMapping("/raids/location/{location}")
    public ResponseEntity<List<Raid>> getRaidByLocation(@PathVariable String location) {
        return new ResponseEntity<>(raidRepository.findByLocationIgnoreCase(location), HttpStatus.OK);
    }


    @GetMapping("/raids/ship/{id}")
    public ResponseEntity<List<Raid>> findByPiratesShipId(@PathVariable Long id) {
        return new ResponseEntity<>(raidRepository.findByPiratesShipId(id), HttpStatus.OK);
    }


    @PostMapping("/raids")
    public ResponseEntity<Raid> createRaid(@RequestBody Raid raid) {
        raidRepository.save(raid);
        return new ResponseEntity<>(raid, HttpStatus.CREATED);
    }

}

