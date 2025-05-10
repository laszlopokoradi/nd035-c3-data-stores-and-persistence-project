package com.udacity.jdnd.course3.critter.dto;

import jakarta.validation.constraints.*;
import java.time.*;
import java.util.*;

/**
 * Represents a request to find available employees by skills. Does not map to the database directly.
 */
public class EmployeeRequestDTO {

    @NotEmpty(message = "Employee must have at least one skill")
    private Set<Long> skills;

    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    public Set<Long> getSkills() {
        return skills;
    }

    public EmployeeRequestDTO setSkills(Set<Long> skills) {
        this.skills = skills;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public EmployeeRequestDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
