package com.udacity.jdnd.course3.critter.dto;


import jakarta.validation.constraints.*;
import java.time.*;
import java.util.*;


/**
 * Represents the form that pet request and response data takes. Does not map to the database
 * directly.
 */
public class PetDTO {

    private UUID id;

    @NotNull(message = "Type cannot be null")
    private Long type;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Owner ID cannot be null")
    private UUID owner;

    @PastOrPresent(message = "Birth date cannot be in the future")
    private LocalDate birthDate;

    @Size(max = 255, message = "Notes cannot be longer than 255 characters")
    private String notes;

    public UUID getId() {
        return id;
    }

    public PetDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public Long getType() {
        return type;
    }

    public PetDTO setType(Long type) {
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetDTO setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        this.name = name;
        return this;
    }

    public UUID getOwner() {
        return owner;
    }

    public PetDTO setOwnerId(UUID owner) {
        if (owner == null) {
            throw new IllegalArgumentException("Owner ID cannot be null");
        }

        this.owner = owner;
        return this;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public PetDTO setBirthDate(LocalDate birthDate) {
        if (birthDate != null && birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Birth date cannot be null or in the future");
        }

        this.birthDate = birthDate;
        return this;
    }

    public String getNotes() {
        return notes;
    }

    public PetDTO setNotes(String notes) {
        this.notes = notes;
        return this;
    }
}
