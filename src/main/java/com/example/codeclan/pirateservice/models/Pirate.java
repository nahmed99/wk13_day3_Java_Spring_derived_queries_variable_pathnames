package com.example.codeclan.pirateservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pirates")
public class Pirate {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // column on which pirate will link to ship + pirate MUST have a ship
    @JoinColumn(name = "ship_id", nullable = false)
    // property that is returned from the model - curly braces if multiple variables need
    // to be added. This will ignore the link from ship back to pirates (that is when recursion occurs).
    // ...don't want any more data after pirates is encountered...
    @JsonIgnoreProperties({"pirates"})
    private Ship ship;

    @ManyToMany
    @JsonIgnoreProperties({ "pirates" })
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(
            name = "pirates_raids",
            joinColumns = { @JoinColumn(
                    name = "pirate_id",
                    nullable = false,
                    updatable = false
            )},
            inverseJoinColumns = { @JoinColumn (
                    name = "raid_id",
                    nullable = false,
                    updatable = false
            ) }
    )
    private List<Raid> raids;


    public Pirate(String firstName, String lastName, int age, Ship ship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ship = ship;
        this.raids = new ArrayList<>();
    }

    // default constructor required for Spring to use.
    public Pirate() {
    }

    public void addRaid(Raid raid) {
        this.raids.add(raid);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Raid> getRaids() {
        return raids;
    }

    public void setRaids(List<Raid> raids) {
        this.raids = raids;
    }
}
