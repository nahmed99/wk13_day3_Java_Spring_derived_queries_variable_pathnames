package com.example.codeclan.pirateservice.controllers;

import com.example.codeclan.pirateservice.models.Raid;
import com.example.codeclan.pirateservice.models.Ship;
import com.example.codeclan.pirateservice.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ShipController {

    @Autowired
    ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> allPirates = shipRepository.findAll();
        return new ResponseEntity<>(allPirates, HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    public ResponseEntity<Optional<Ship>> getPirate(@PathVariable Long id){
        return new ResponseEntity<>(shipRepository.findById(id), HttpStatus.OK);
    }

    // SHOW (by pirate firstName)
    @GetMapping("/ships/pirates/{firstName}")
    public ResponseEntity<List<Ship>> getShipsByPirateFirstName(@PathVariable String firstName) {
        return new ResponseEntity<>(shipRepository.findByPiratesFirstName(firstName), HttpStatus.OK);
    }


    @PostMapping("/ships")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        shipRepository.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }

}

