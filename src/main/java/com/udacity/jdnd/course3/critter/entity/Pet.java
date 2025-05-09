package com.udacity.jdnd.course3.critter.entity;


import jakarta.persistence.*;

import java.time.*;
import java.util.*;


@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PetType type;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;

    @Column(name = "notes", nullable = true)
    private String notes;

    public Pet setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Pet setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }

        this.birthDate = birthDate;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public Pet setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public Customer getOwner() {
        return owner;
    }

    public Pet setOwner(Customer owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner cannot be null");
        }

        this.owner = owner;
        owner.addPet(this);

        return this;
    }

    public PetType getType() {
        return type;
    }

    public Pet setType(PetType type) {
        this.type = type;
        return this;
    }
}
