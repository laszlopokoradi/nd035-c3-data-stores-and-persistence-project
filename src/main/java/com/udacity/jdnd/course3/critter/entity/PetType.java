package com.udacity.jdnd.course3.critter.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "pet_types")
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "pet_type_activities",
            joinColumns = @JoinColumn(name = "pet_type_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities = new HashSet<>();

    public Long getId() {
        return id;
    }

    public PetType setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetType setName(String name) {
        this.name = name;
        return this;
    }
}