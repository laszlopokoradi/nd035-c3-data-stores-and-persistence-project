package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import org.springframework.stereotype.*;
import java.util.*;


@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> getPetById(UUID id) {
        return this.petRepository.findById(id);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void deletePet(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Pet ID cannot be null");
        }

        if (!petRepository.existsById(id)) {
            throw new IllegalArgumentException("Pet with ID " + id + " does not exist");
        }

        petRepository.deleteById(id);
    }

    public List<Pet> getPetsByOwner(UUID ownerId) {
        if (ownerId == null) {
            throw new IllegalArgumentException("Owner ID cannot be null");
        }

        return petRepository.findByOwnerId(ownerId);
    }
}
