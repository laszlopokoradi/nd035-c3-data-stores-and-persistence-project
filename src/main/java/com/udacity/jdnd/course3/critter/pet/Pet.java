package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.user.*;
import jakarta.persistence.*;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PetType type;

    @Column(name ="name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer owner;

    @Column(name = "birth_date", nullable = true)
    private LocalDate birthDate;
    private String notes;

    protected Pet setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getId() {
        return id;
    }

    protected LocalDate getBirthDate() {
        return birthDate;
    }

    protected Pet setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }

        if (birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be in the future");
        }

        this.birthDate = birthDate;
        return this;
    }

    protected String getName() {
        return name;
    }

    protected Pet setName(String name) {
        this.name = name;
        return this;
    }

    protected String getNotes() {
        return notes;
    }

    protected Pet setNotes(String notes) {
        this.notes = notes;
        return this;
    }

    protected Customer getOwner() {
        return owner;
    }

    protected Pet setOwner(Customer owner) {
        this.owner = owner;
        return this;
    }

    protected PetType getType() {
        return type;
    }

    protected Pet setType(PetType type) {
        this.type = type;
        return this;
    }
}
