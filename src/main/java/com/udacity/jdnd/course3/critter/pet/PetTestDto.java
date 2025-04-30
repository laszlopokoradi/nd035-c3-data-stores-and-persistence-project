package com.udacity.jdnd.course3.critter.pet;


import jakarta.validation.constraints.*;

import java.io.*;
import java.time.*;
import java.util.*;


/**
 * DTO for {@link Pet}
 */
public class PetTestDto
        implements Serializable {
    private final UUID id;
    private final PetType type;
    @NotBlank
    private final String name;
    private final UUID ownerId;
    @PastOrPresent
    private final LocalDate birthDate;
    private final String notes;

    protected PetTestDto(UUID id, PetType type, String name, UUID ownerId, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ownerId = ownerId;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public UUID getId() {
        return id;
    }

    public PetType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PetTestDto entity = (PetTestDto) o;
        return Objects.equals(this.id, entity.id) &&
               Objects.equals(this.type, entity.type) &&
               Objects.equals(this.name, entity.name) &&
               Objects.equals(this.ownerId, entity.ownerId) &&
               Objects.equals(this.birthDate, entity.birthDate) &&
               Objects.equals(this.notes, entity.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, ownerId, birthDate, notes);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + id + ", " +
               "type = " + type + ", " +
               "name = " + name + ", " +
               "ownerId = " + ownerId + ", " +
               "birthDate = " + birthDate + ", " +
               "notes = " + notes + ")";
    }
}