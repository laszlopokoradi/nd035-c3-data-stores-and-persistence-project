package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING
, uses = {ActivityResolver.class})
public interface EmployeeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "skills", source = "skills")
    @Mapping(target = "daysAvailable", source = "daysAvailable")
    Employee toEntity(EmployeeDTO employeeDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Employee updateFromDTO(EmployeeDTO employeeDTO, @MappingTarget Employee employee);

    @Mapping(target = "skills", source = "skills")
    @Mapping(target = "daysAvailable", source = "daysAvailable")
    EmployeeDTO toDTO(Employee employee);
}