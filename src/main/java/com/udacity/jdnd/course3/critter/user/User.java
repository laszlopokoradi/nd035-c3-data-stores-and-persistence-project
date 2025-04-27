package com.udacity.jdnd.course3.critter.user;


import jakarta.persistence.*;
import java.util.*;
import org.hibernate.annotations.*;
import org.hibernate.type.*;


@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    protected UUID id;

    @Column(name = "name", nullable = false)
    protected String name;

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

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
