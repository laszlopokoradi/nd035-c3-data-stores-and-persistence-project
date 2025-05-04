package com.udacity.jdnd.course3.critter.mapper.pet;

import com.udacity.jdnd.course3.critter.pet.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {PetCustomerResolver.class})
public interface PetMapper {
    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "id", ignore = true)
    Pet toEntity(PetDTO dto);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "id", ignore = true)
    void updateFromDto(PetDTO dto, @MappingTarget Pet pet);

    @Mapping(source = "owner", target = "ownerId")
    PetDTO toDto(Pet pet);
}
