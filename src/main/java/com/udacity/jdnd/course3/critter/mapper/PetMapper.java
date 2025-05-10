package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CustomerResolver.class, PetTypeResolver.class})
public interface PetMapper {

    @Mapping(target = "id", ignore = true)
    Pet toEntity(PetDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pet updateFromDTO(PetDTO dto, @MappingTarget Pet pet);

    @Mapping(source = "owner", target = "ownerId")
    PetDTO toDTO(Pet pet);
}
