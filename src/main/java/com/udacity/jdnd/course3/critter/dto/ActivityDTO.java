package com.udacity.jdnd.course3.critter.dto;

import jakarta.validation.constraints.*;
import java.io.*;


public class ActivityDTO implements Serializable {
    private Long id;

    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public ActivityDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ActivityDTO setName(String name) {
        this.name = name;
        return this;
    }
}