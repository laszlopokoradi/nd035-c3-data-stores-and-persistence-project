package com.udacity.jdnd.course3.critter.entity;


import jakarta.persistence.*;

import java.time.*;
import java.util.*;


@Entity
@Table(name = "employees")
public class Employee extends User {

    @ManyToMany
    @JoinTable(
            name = "employee_activities",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(name = "employee_days_available", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.ORDINAL)
    private Set<DayOfWeek> daysAvailable;

    @Override
    public Employee setId(UUID id) {
        super.setId(id);
        return this;
    }

    @Override
    public Employee setName(String name) {
        super.setName(name);
        return this;
    }

    public Set<Activity> getSkills() {
        return skills;
    }

    public Employee setSkills(
            Set<Activity> skills) {
        this.skills = skills;
        return this;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public Employee setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
        return this;
    }
}
