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

    // REST GET all
    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips() {
        List<Ship> allPirates = shipRepository.findAll();
        return new ResponseEntity<>(allPirates, HttpStatus.OK);
    }


    // REST GET by Id
    @GetMapping(value = "/ships/{id}")
    public ResponseEntity<Optional<Ship>> getPirate(@PathVariable Long id){
        return new ResponseEntity<>(shipRepository.findById(id), HttpStatus.OK);
    }


    /**
     *  SHOW (by pirate firstName) using a passed in parameter:
     *
     *   GET /ships/pirates?firstName=whatever
     *   The word 'named' above will be referenced within the method below...
     */
    @GetMapping("/ships/pirates")
    public ResponseEntity<List<Ship>> findByPiratesFirstName(
            @RequestParam(name = "firstName") String firstName
    ) {
        return new ResponseEntity<>(shipRepository.findByPiratesFirstName(firstName), HttpStatus.OK);
    }


    // REST Post
    @PostMapping("/ships")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        shipRepository.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }

}

