package com.udacity.jdnd.course3.critter.entity;


import java.time.*;
import java.util.*;

public class EmployeeRequest {

    private Set<Activity> skills;

    private LocalDate date;

    public Set<Activity> getSkills() {
        return skills;
    }

    public EmployeeRequest setSkills(Set<Activity> skills) {
        this.skills = skills;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public EmployeeRequest setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
