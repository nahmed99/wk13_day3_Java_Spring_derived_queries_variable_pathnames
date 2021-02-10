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

//    @GetMapping(value = "/raids")
//    public ResponseEntity<List<Raid>> getAllRaids() {
//        List<Raid> allRaids = raidRepository.findAll();
//        return new ResponseEntity<>(allRaids, HttpStatus.OK);
//    }


    /**
     * GET /raids                           null, null, null        - findAll
     * GET /raids?location=location_name    location, null, null    - findByLocationIgnoreCase
     * GET /raids?shipId=id                 null, shipId, null      - findPiratesByShipId
     * GET /raids?loot=number               null, null, loot        - findByLoot
     * GET /raids?lootStart=num&lootEnd=num null, null, null, num   - findBy
     */

    @GetMapping(value = "/raids")
    public ResponseEntity<List<Raid>> getAllRaids(
            // name="some_string", some_string will be passed into the defined/related parameter
            @RequestParam(name="location", required = false) String locationName,
            @RequestParam(name="shipId", required = false) Long shipId,
            @RequestParam(name="loot", required = false) Integer loot

    ) {

        // if location != null - do location query and return result
        if (locationName != null) {
            List<Raid> allRaids = raidRepository.findByLocationIgnoreCase(locationName);
            return new ResponseEntity<>(allRaids, HttpStatus.OK);
        }
        // if shipId != null - do ship query and return result
        if (shipId != null) {
            List<Raid> allRaids = raidRepository.findByPiratesShipId(shipId);
            return new ResponseEntity<>(allRaids, HttpStatus.OK);
        }
        if (loot != null) {
            List<Raid> allRaids = raidRepository.findByLoot(loot);
            return new ResponseEntity<>(allRaids, HttpStatus.OK);
        }
        // otherwise do the find all query
        List<Raid> allRaids = raidRepository.findAll();
        return new ResponseEntity<>(allRaids, HttpStatus.OK);
    }


    // SHOW (by id)
    @GetMapping(value = "/raids/{id}")
    public ResponseEntity<Optional<Raid>> getRaid(@PathVariable Long id){
        return new ResponseEntity<>(raidRepository.findById(id), HttpStatus.OK);
    }

    // The follow two methods have been replaced with the ? query above.
//    // SHOW (by location)
//    @GetMapping("/raids/location/{location}")
//    public ResponseEntity<List<Raid>> getRaidByLocation(@PathVariable String location) {
//        return new ResponseEntity<>(raidRepository.findByLocationIgnoreCase(location), HttpStatus.OK);
//    }
//
//
//    @GetMapping("/raids/ship/{id}")
//    public ResponseEntity<List<Raid>> findByPiratesShipId(@PathVariable Long id) {
//        return new ResponseEntity<>(raidRepository.findByPiratesShipId(id), HttpStatus.OK);
//    }


    @PostMapping("/raids")
    public ResponseEntity<Raid> createRaid(@RequestBody Raid raid) {
        raidRepository.save(raid);
        return new ResponseEntity<>(raid, HttpStatus.CREATED);
    }

}

