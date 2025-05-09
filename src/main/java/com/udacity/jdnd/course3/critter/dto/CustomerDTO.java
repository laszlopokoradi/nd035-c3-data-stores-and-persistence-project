package com.udacity.jdnd.course3.critter.dto;


import jakarta.validation.constraints.*;

import java.util.*;


/**
 * Represents the form that customer request and response data takes. Does not map to the database
 * directly.
 */
public class CustomerDTO {
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String notes;

    private List<UUID> petIds = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public CustomerDTO setId(UUID id) {
        this.id = id;

        return this;
    }

    public String getName() {
        return name;
    }

    public CustomerDTO setName(String name) {
        this.name = name;

        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

        return this;
    }

    public String getNotes() {
        return notes;
    }

    public CustomerDTO setNotes(String notes) {
        this.notes = notes;

        return this;
    }

    public List<UUID> getPetIds() {
        return petIds;
    }

    public CustomerDTO setPetIds(List<UUID> petIds) {
        this.petIds = petIds;

        return this;
    }
}
