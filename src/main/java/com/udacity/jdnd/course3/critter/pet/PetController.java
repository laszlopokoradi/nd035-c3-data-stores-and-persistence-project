package com.udacity.jdnd.course3.critter.pet;

import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet savedPet = this.petService.savePet(petDTO.toEntity());

        return PetDTO.fromEntity(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable UUID petId) {
        Pet pet = this.petService.getPetById(petId);

        return PetDTO.fromEntity(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = this.petService.getAllPets();
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOs.add(PetDTO.fromEntity(pet));
        }

        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable UUID ownerId) {
        List<Pet> pets = this.petService.getPetsByOwner(ownerId);
        List<PetDTO> petDTOs = new ArrayList<>();

        for (Pet pet : pets) {
            petDTOs.add(PetDTO.fromEntity(pet));
        }

        return petDTOs;
    }
}
