package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.*;
import jakarta.persistence.*;

import java.util.*;


@Entity
@Table(name = "customers")
public class Customer extends User {
    @Column
    protected String phoneNumber;
    @Column
    protected String notes;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    protected List<Pet> pets;


    public Customer setId(UUID id) {
        this.id = id;
        return this;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Customer setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Customer setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public Customer setPets(List<Pet> pets) {
        this.pets = pets;
        return this;
    }
}
