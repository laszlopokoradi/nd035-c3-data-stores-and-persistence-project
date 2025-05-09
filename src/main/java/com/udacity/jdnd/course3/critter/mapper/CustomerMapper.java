package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;
import org.mapstruct.MappingConstants.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING,
        uses = {PetResolver.class, CustomerResolver.class})
public interface CustomerMapper {
    @Mapping(source = "petIds", target = "pets")
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerDTO customerDTO );

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(target = "id", ignore = true)
    void updateFromDto(PetDTO dto, @MappingTarget Pet pet);

    @Mapping(source = "pets", target = "petIds")
    CustomerDTO toDto(Customer customer);
}