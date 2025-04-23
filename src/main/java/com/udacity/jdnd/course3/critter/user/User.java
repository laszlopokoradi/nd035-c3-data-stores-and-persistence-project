package com.udacity.jdnd.course3.critter.user;


import jakarta.persistence.*;
import org.hibernate.annotations.*;
import org.hibernate.type.*;

import java.util.*;


@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    protected UUID id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "phone_number", nullable = true)
    protected String phoneNumber;

    @Column(name = "notes", nullable = true)
    protected String notes;

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
