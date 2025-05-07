package com.udacity.jdnd.course3.critter.user;


import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, UUID> {
    @Query("SELECT c FROM Customer c JOIN FETCH c.pets p WHERE p.id = :petId")
    Optional<Customer> findByPetId(UUID petId);
}