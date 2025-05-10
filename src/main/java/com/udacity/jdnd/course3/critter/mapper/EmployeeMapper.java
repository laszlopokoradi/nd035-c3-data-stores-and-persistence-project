package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING
, uses = {ActivityResolver.class})
public interface EmployeeMapper {
    Employee toEntity(EmployeeDTO employeeDTO);

    EmployeeDTO toDTO(Employee employee);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee updateFromDTO(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
}