package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
public class PetTypeService {
    private final PetTypeRepository petTypeRepository;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    public PetType savePetType(PetType petType) {
        return petTypeRepository.save(petType);
    }

    public Optional<PetType> getPetTypeById(Long id) {
        return petTypeRepository.findById(id);
    }

    public List<PetType> getAllPetTypes() {
        return petTypeRepository.findAll();
    }

    public void deletePetTypeById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
