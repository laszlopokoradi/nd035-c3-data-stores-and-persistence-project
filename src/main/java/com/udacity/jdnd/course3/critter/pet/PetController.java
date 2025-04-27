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

        // throw new UnsupportedOperationException();
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable UUID petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable UUID ownerId) {
        throw new UnsupportedOperationException();
    }
}
