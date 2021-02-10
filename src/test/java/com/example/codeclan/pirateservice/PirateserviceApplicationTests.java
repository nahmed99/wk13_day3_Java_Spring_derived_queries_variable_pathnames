package com.example.codeclan.pirateservice;

import com.example.codeclan.pirateservice.models.Pirate;
import com.example.codeclan.pirateservice.models.Raid;
import com.example.codeclan.pirateservice.models.Ship;
import com.example.codeclan.pirateservice.repositories.PirateRepository;
import com.example.codeclan.pirateservice.repositories.RaidRepository;
import com.example.codeclan.pirateservice.repositories.ShipRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@AutoConfigureTestDatabase - database was NOT updating with this annotation, so commented it out!!!
@SpringBootTest
class PirateserviceApplicationTests {

	@Autowired
	PirateRepository pirateRepository; // do you have an instance of PirateRepository

	@Autowired
	ShipRepository shipRepository; // do you have an instance of PirateRepository

	@Autowired
	RaidRepository raidRepository;


	@Test
	void contextLoads() {
	}

	@Test
	public void canCreateAndSavePirate(){

		Ship ship = new Ship("The Flying Dutchman");
		shipRepository.save(ship); // save ship to db

		// create a pirate
		Pirate pirate = new Pirate("Jackie", "Sparra", 32, ship);
		// get hold of the pirate repo' and use it to save the pirate to the DB
		pirateRepository.save(pirate);

		Raid raid = new Raid("Tortuga", 1110);
		raidRepository.save(raid);

		raid.addPirate(pirate);
		raidRepository.save(raid);
	}

	@Test
	public void canGetPiratesOverAge30() {

		List<Pirate> foundPirates = pirateRepository.findByAgeGreaterThan(30);
		assertEquals(8, foundPirates.size());
	}

	@Test
	public void canGetRaidsByLocation() {

		List<Raid> foundRaids = raidRepository.findByLocationIgnoreCase("Tortuga");
		assertEquals(8, foundRaids.size());
	}


}
