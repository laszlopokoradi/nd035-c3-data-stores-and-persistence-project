package com.udacity.jdnd.course3.critter.user;


import jakarta.persistence.*;

import java.time.*;
import java.util.*;


@Entity
public class Employee extends User {
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public Employee setId(UUID id) {
        super.setId(id);
        return this;
    }

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
