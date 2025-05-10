package com.udacity.jdnd.course3.critter.dto;

import jakarta.validation.constraints.*;
import java.time.*;
import java.util.*;

/**
 * Represents the form that employee request and response data takes. Does not map to the database directly.
 */

public class EmployeeDTO {

    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotEmpty(message = "Employee must have at least one skill")
    private Set<Long> skills;

    private Set<DayOfWeek> daysAvailable;

    public UUID getId() {
        return id;
    }

    public EmployeeDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Long> getSkills() {
        return skills;
    }

    public EmployeeDTO setSkills(Set<Long> skills) {
        this.skills = skills;
        return this;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public EmployeeDTO setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
        return this;
    }
}
