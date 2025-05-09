package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.*;
import jakarta.validation.constraints.*;

import java.time.DayOfWeek;
import java.util.*;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */

public class EmployeeDTO {
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotEmpty(message = "Employee must have at least one skill")
    private Set<EmployeeSkill> skills;

    @NotEmpty(message = "Employee must have at least one day available")
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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public EmployeeDTO setSkills(Set<EmployeeSkill> skills) {
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
