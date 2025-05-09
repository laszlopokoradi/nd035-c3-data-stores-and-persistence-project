package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;
import com.udacity.jdnd.course3.critter.service.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;
    private final PetMapper petMapper;

    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody @Valid PetDTO petDTO) {
        Pet savedPet = this.petService.savePet(this.petMapper.toEntity(petDTO));

        return this.petMapper.toDto(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable UUID petId) {
        Optional<Pet> pet = this.petService.getPetById(petId);

        return pet
                .map(this.petMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Pet with id %s not found".formatted(petId)));
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = this.petService.getAllPets();

        return pets.stream()
                .map(this.petMapper::toDto)
                .toList();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable UUID ownerId) {
        List<Pet> pets = this.petService.getPetsByOwner(ownerId);

        return pets.stream()
                .map(this.petMapper::toDto)
                .toList();
    }
}
