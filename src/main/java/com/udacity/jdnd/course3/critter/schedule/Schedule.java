package com.udacity.jdnd.course3.critter.schedule;


import com.udacity.jdnd.course3.critter.pet.*;
import com.udacity.jdnd.course3.critter.user.*;
import jakarta.persistence.*;

import java.time.*;
import java.util.*;


@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "schedule_employees",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employeeIds = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "schedule_pets",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets = new ArrayList<>();

    @Column(name = "date")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public List<Employee> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Employee> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public Long getId() {
        return id;
    }

    public Schedule setId(Long id) {
        this.id = id;

        return this;
    }
}