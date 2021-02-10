package com.example.codeclan.pirateservice.repositories;

import com.example.codeclan.pirateservice.models.Pirate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // when spring starts create an instance of PirateRepository for later
public interface PirateRepository extends JpaRepository<Pirate, Long> {

    List<Pirate> findByAgeGreaterThan(int age);

    List<Pirate> findByAge(int age);
    List<Pirate> findByFirstName(String name);
    List<Pirate> findDistinctByFirstName(String name);
    List<Pirate> findDistinctByLastNameOrFirstName(String lastName, String firstName);



}
