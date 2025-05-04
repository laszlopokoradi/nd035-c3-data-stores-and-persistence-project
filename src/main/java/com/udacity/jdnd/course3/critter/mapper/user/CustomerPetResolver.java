package com.udacity.jdnd.course3.critter.mapper.user;

import com.udacity.jdnd.course3.critter.pet.*;
import jakarta.persistence.*;
import java.util.*;
import org.springframework.stereotype.*;

@Component
public class CustomerPetResolver {

    private final PetRepository petRepository;

    public CustomerPetResolver(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet resolvePet(UUID petId) {
        if (petId == null) {
            return null;
        }
        return petRepository.findById(petId)
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Pet not found with id: " + petId));
    }
}
