package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

@Component
public class PetTypeResolver {
    private final PetTypeRepository petTypeRepository;

    public PetTypeResolver(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    public PetType resolvePetTypeId(Long petTypeId) {
        if (petTypeId == null) {
            return null;
        }
        return petTypeRepository.findById(petTypeId)
                                 .orElseThrow(() -> new EntityNotFoundException(
                                         "Pet type not found with id: " + petTypeId));
    }

    public Long resolvePetType(PetType petType) {
        if (petType == null) {
            return null;
        }
        return petType.getId();
    }

}
