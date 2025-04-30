package com.udacity.jdnd.course3.critter.pet;

import org.mapstruct.*;
import org.mapstruct.Mapping;


public interface PetMapStructMapper {
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "id", ignore = true)
    Pet convertToEntity(PetDTO dto);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(PetDTO dto, @MappingTarget Pet pet);
}
