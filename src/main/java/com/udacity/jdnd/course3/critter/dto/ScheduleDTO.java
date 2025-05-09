package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.*;

import java.time.LocalDate;
import java.util.*;


/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private Long id;
    private List<UUID> employeeIds;
    private List<UUID> petIds;
    private LocalDate date;
    private Set<Activity> activities;

    public Long getId(){
        return id;
    }
    
    public ScheduleDTO setId(Long id){
        this.id = id;
        return this;
    }
    
    public List<UUID> getEmployeeIds() {
        return employeeIds;
    }

    public ScheduleDTO setEmployeeIds(List<UUID> employeeIds) {
        this.employeeIds = employeeIds;
        return this;
    }

    public List<UUID> getPetIds() {
        return petIds;
    }

    public ScheduleDTO setPetIds(List<UUID> petIds) {
        this.petIds = petIds;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public ScheduleDTO setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public ScheduleDTO setActivities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }
}
