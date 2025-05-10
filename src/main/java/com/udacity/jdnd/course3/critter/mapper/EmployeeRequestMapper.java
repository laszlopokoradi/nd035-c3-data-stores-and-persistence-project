package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;
import org.mapstruct.MappingConstants.*;
import org.springframework.jmx.export.annotation.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING,
        uses = {ActivityResolver.class})
public interface EmployeeRequestMapper {

    EmployeeRequest toEntity(EmployeeRequestDTO employeeRequestDTO);

    EmployeeRequestDTO toDTO(EmployeeRequest employeeRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmployeeRequest updateFromDTO(
            EmployeeRequestDTO employeeRequestDTO, @MappingTarget EmployeeRequest employeeRequest);
}