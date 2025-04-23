package com.udacity.jdnd.course3.critter.pet;


import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface PetRepository
        extends JpaRepository<Pet, UUID> {
}
