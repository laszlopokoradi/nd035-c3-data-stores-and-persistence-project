package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.user.*;
import java.time.*;
import java.util.*;


/**
 * Represents the form that pet request and response data takes. Does not map to the database
 * directly.
 */
public class PetDTO {

    private UUID id;
    private PetType type;
    private String name;
    private UUID ownerId;
    private LocalDate birthDate;
    private String notes;

    public static PetDTO fromEntity(Pet savedPet) {
        PetDTO petDTO = new PetDTO();
        petDTO.setId(savedPet.getId())
               .setType(savedPet.getType())
               .setName(savedPet.getName())
               .setOwnerId(savedPet.getOwner().getId())
               .setBirthDate(savedPet.getBirthDate())
               .setNotes(savedPet.getNotes());

        return petDTO;
    }


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


    public Pet toEntity() {
        Pet pet = new Pet();
        pet.setId(this.id)
           .setType(this.type)
           .setName(this.name)
           .setOwner(new Customer().setId(this.ownerId))
           .setBirthDate(this.birthDate)
           .setNotes(this.notes);

        return pet;
    }
}
