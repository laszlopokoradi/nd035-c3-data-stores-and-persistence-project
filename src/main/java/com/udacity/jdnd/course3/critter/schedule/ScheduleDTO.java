package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import java.time.LocalDate;
import java.util.*;


/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
public class ScheduleDTO {
    private UUID id;
    private List<UUID> employeeIds;
    private List<UUID> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public UUID getId(){
        return id;
    }
    
    public void setId(UUID id){
        this.id = id;
    }
    
    public List<UUID> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<UUID> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public List<UUID> getPetIds() {
        return petIds;
    }

    public void setPetIds(List<UUID> petIds) {
        this.petIds = petIds;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
