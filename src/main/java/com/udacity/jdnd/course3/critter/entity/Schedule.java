package com.udacity.jdnd.course3.critter.entity;


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
    private List<Employee> employees = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "schedule_pets",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    private List<Pet> pets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "schedule_activities",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities = new HashSet<>();

    @Column(name = "date")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public Schedule setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public Schedule setPets(List<Pet> pets) {
        this.pets = pets;
        return this;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Schedule setEmployees(List<Employee> employeeIds) {
        this.employees = employeeIds;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Schedule setId(Long id) {
        this.id = id;
        return this;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Schedule setActivities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }
}