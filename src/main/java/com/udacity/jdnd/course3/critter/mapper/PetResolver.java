package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import jakarta.persistence.*;
import java.util.*;
import org.springframework.stereotype.*;

@Component
public class PetResolver {

    private final PetRepository petRepository;

    public PetResolver(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet resolvePetId(UUID petId) {
        if (petId == null) {
            return null;
        }
        return petRepository.findById(petId)
                            .orElseThrow(() -> new EntityNotFoundException(
                                    "Pet not found with id: " + petId));
    }

    public UUID resolvePet(Pet pet) {
        if (pet == null) {
            return null;
        }

        return pet.getId();
    }

    public List<UUID> map(List<Pet> pets) {
        if (pets == null) {
            return Collections.emptyList();
        }

        return pets.stream()
                    .map(this::resolvePet)
                    .toList();
    }
}
