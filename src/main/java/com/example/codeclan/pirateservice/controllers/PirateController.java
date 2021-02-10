package com.example.codeclan.pirateservice.controllers;

import com.example.codeclan.pirateservice.models.Pirate;
import com.example.codeclan.pirateservice.models.Raid;
import com.example.codeclan.pirateservice.repositories.PirateRepository;
import com.example.codeclan.pirateservice.repositories.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // oi spring plz make me dis when u get a chance kthhx bai
public class PirateController {

    @Autowired
    PirateRepository pirateRepository;

    @Autowired
    RaidRepository raidRepository;


    @GetMapping(value ="/pirates")
    public ResponseEntity<List<Pirate>> getAllPirates(){
        List<Pirate> allPirates = pirateRepository.findAll();
        return new ResponseEntity<>(allPirates, HttpStatus.OK);
    }

    @GetMapping(value = "/pirates/{id}")
    public ResponseEntity<Optional<Pirate>> getPirate(@PathVariable Long id){
        return new ResponseEntity<>(pirateRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/pirates")
    public ResponseEntity<Pirate> createPirate(@RequestBody Pirate pirate) {
        pirateRepository.save(pirate);
        return new ResponseEntity<>(pirate, HttpStatus.CREATED);
    }

    // GET raids from a specific pirate
    @GetMapping("/pirates/{id}/raids")
    public ResponseEntity<List<Raid>> getRaidsFromPirate(@PathVariable Long id) {
        List<Raid> raids = raidRepository.findByPiratesId(id);
        return new ResponseEntity<>(raids, HttpStatus.OK);
    }

}
