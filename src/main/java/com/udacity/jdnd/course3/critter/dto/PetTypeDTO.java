package com.udacity.jdnd.course3.critter.dto;

import jakarta.validation.constraints.*;
import java.io.*;
import java.util.*;

public class PetTypeDTO implements Serializable {
    private Long id;
    @NotBlank
    private String name;
    private Set<Long> activities;

    public Long getId() {
        return id;
    }

    public PetTypeDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetTypeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Long> getActivities() {
        return activities;
    }

    public PetTypeDTO setActivities(Set<Long> activities) {
        this.activities = activities;
        return this;
    }
}