package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.mapper.*;
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
        Pet pet = this.petService.getPetById(petId);

        return this.petMapper.toDto(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = this.petService.getAllPets();
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOs.add(this.petMapper.toDto(pet));
        }

        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable UUID ownerId) {
        List<Pet> pets = this.petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOs.add(this.petMapper.toDto(pet));
        }

        return petDTOs;
    }
}
