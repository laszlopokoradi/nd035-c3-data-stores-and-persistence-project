package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * Represents a request to find available employees by skills. Does not map
 * to the database directly.
 */
public class EmployeeRequestDTO {
    @NotEmpty(message = "Employee must have at least one skill")
    private Set<EmployeeSkill> skills;

    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
