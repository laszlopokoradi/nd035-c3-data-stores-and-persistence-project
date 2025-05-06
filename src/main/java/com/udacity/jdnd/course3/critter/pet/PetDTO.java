package com.udacity.jdnd.course3.critter.pet;


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
    private PetType type;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Owner ID cannot be null")
    private UUID ownerId;

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

    public PetType getType() {
        return type;
    }

    public PetDTO setType(PetType type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetDTO setName(String name) {
        this.name = name;
        return this;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public PetDTO setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
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
