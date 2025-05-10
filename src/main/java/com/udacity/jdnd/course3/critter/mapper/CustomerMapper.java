package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;
import org.mapstruct.MappingConstants.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING,
        uses = {PetResolver.class, CustomerResolver.class, PetTypeResolver.class})
public interface CustomerMapper {
    @Mapping(target = "id", ignore = true)
    Customer toEntity(CustomerDTO customerDTO );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer updateFromDTO(CustomerDTO dto, @MappingTarget Customer pet);

    @Mapping(source = "pets", target = "pets")
    CustomerDTO toDTO(Customer customer);
}