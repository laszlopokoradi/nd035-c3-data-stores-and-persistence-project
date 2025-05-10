package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING
, uses = {PetResolver.class, CustomerResolver.class, EmployeeResolver.class, ActivityResolver.class})
public interface ScheduleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pets", source = "pets")
    @Mapping(target = "employees", source = "employees")
    Schedule toEntity(ScheduleDTO scheduleDTO);

    @Mapping(target = "pets", source = "pets")
    @Mapping(target = "employees", source = "employees")
    ScheduleDTO toDTO(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule updateFromDTO(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
}