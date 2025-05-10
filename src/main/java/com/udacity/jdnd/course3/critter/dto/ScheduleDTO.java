package com.udacity.jdnd.course3.critter.dto;

import java.time.LocalDate;
import java.util.*;


/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private Long id;
    private List<UUID> employees;
    private List<UUID> pets;
    private LocalDate date;
    private Set<Long> activities;

    public Long getId(){
        return id;
    }
    
    public ScheduleDTO setId(Long id){
        this.id = id;
        return this;
    }
    
    public List<UUID> getEmployees() {
        return employees;
    }

    public ScheduleDTO setEmployees(List<UUID> employees) {
        this.employees = employees;
        return this;
    }

    public List<UUID> getPets() {
        return pets;
    }

    public ScheduleDTO setPets(List<UUID> pets) {
        this.pets = pets;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ScheduleDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Set<Long> getActivities() {
        return activities;
    }

    public ScheduleDTO setActivities(Set<Long> activities) {
        this.activities = activities;
        return this;
    }
}
