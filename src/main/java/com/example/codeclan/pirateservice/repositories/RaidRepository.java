package com.example.codeclan.pirateservice.repositories;

import com.example.codeclan.pirateservice.models.Raid;
import com.example.codeclan.pirateservice.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaidRepository extends JpaRepository<Raid, Long> {

    // get raids by pirate id
    List<Raid> findByPiratesId(Long pirateId);

    // Derived query to find raids by location
    List<Raid> findByLocationIgnoreCase(String location);

    // Find all the raids that a given ship has gone on
    List<Raid> findByPiratesShipId(Long shipId);

    List<Raid> findByLoot(int loot);

//    List<Raid> findByLootBetween(int loot);
}
