package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING
, uses = {PetResolver.class, CustomerResolver.class, EmployeeResolver.class, ActivityResolver.class})
public interface ScheduleMapper {
    @Mapping(target = "employees", source = "employeeIds")
    @Mapping(target = "pets", source = "petIds")
    Schedule toEntity(ScheduleDTO scheduleDTO);
    
    @Mapping(target = "petIds", source = "pets")
    @Mapping(target = "employeeIds", source = "employees")
    @Mapping(target = "activities", source = "activities")
    ScheduleDTO toDTO(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule updateFromDTO(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
}