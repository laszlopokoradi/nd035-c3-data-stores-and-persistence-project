package com.udacity.jdnd.course3.critter.user;


import jakarta.persistence.*;
import org.apache.catalina.users.*;

import java.time.*;
import java.util.*;


@Entity
public class Employee extends User {
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public Employee setId(UUID id) {
        super.setId(id);
        return this;
    }


}
