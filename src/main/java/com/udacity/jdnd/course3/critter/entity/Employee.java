package com.udacity.jdnd.course3.critter.entity;


import jakarta.persistence.*;

import java.time.*;
import java.util.*;


@Entity
@Table(name = "employees")
public class Employee extends User {

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable(name = "employee_skills", joinColumns = @JoinColumn(name = "employee_id"))
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public Employee setSkills(
            Set<EmployeeSkill> skills) {
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
