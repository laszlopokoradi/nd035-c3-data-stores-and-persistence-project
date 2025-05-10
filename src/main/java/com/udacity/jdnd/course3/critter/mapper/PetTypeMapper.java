package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;
import org.mapstruct.MappingConstants.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING
, uses = {ActivityResolver.class})
public interface PetTypeMapper {
    PetType toEntity(PetTypeDTO petTypeDTO);

    PetTypeDTO toDTO(PetType petType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PetType updateFromDTO(
            PetTypeDTO petTypeDTO, @MappingTarget PetType petType);
}