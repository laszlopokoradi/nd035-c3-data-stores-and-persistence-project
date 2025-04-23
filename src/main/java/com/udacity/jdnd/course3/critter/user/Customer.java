package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.*;
import jakarta.persistence.*;
import org.hibernate.annotations.*;
import org.hibernate.type.*;

import java.util.*;


@Entity
public class Customer extends User {
    protected String name;
    protected String phoneNumber;
    protected String notes;
    protected List<Pet> pets;


    public Customer setId(UUID id) {
        this.id = id;
        return this;
    }
}
