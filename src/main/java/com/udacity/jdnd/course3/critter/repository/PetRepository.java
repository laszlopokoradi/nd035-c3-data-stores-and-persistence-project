package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface PetRepository
        extends JpaRepository<Pet, UUID> {
    @Query("SELECT p FROM Pet p WHERE p.owner.id = :ownerId")
    List<Pet> findByOwnerId(UUID ownerId);
}
