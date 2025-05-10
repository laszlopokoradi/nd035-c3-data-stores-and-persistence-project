package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;
import com.udacity.jdnd.course3.critter.service.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/pet-types")
public class PetTypeController {

    private final PetTypeService petTypeService;
    private final PetTypeMapper petTypeMapper;

    public PetTypeController(PetTypeService petTypeService, PetTypeMapper petTypeMapper) {
        this.petTypeService = petTypeService;
        this.petTypeMapper = petTypeMapper;
    }

    @GetMapping
    public List<PetTypeDTO> getAllPetTypes() {
        List<PetType> petTypes = petTypeService.getAllPetTypes();

        return petTypes.stream()
                         .map(petTypeMapper::toDTO)
                         .toList();
    }

    @GetMapping("/{id}")
    public PetTypeDTO getPet(@PathVariable Long id) {
        Optional<PetType> petType = this.petTypeService.getPetTypeById(id);

        return petType
                .map(petTypeMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Pet type not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetTypeDTO createPetType(@Valid @RequestBody PetTypeDTO activityDTO) {
        PetType createdPetType = petTypeService.savePetType(
                petTypeMapper.toEntity(activityDTO));

        return petTypeMapper.toDTO(createdPetType);
    }

    @PutMapping("/{id}")
    public PetTypeDTO updatePetType(@PathVariable Long id,
            @Valid @RequestBody PetTypeDTO petTypeDTO) {
        if (petTypeDTO.getId() != null && !petTypeDTO.getId().equals(id)) {
            throw new IllegalArgumentException(
                    "Pet type id in path (%s) does not match id in body (%s)".formatted(id, petTypeDTO.getId()));
        }

        Optional<PetType> petType = this.petTypeService.getPetTypeById(id);

        PetType pt;
        if (petType.isPresent()) {
            pt = petType.get();
            petTypeMapper.updateFromDTO(petTypeDTO, pt);
        } else {
            throw new EntityNotFoundException("No petType (id: %s) found".formatted(id));
        }

        return petTypeMapper.toDTO(pt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetType(@PathVariable Long id) {
        petTypeService.deletePetTypeById(id);
    }

}
