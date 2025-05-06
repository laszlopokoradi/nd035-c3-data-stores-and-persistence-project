package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.schedule.*;
import org.mapstruct.*;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING
, uses = {PetResolver.class, CustomerResolver.class, EmployeeResolver.class})
public interface ScheduleMapper {
    @Mapping(target = "id", source = "id")
    Schedule toEntity(ScheduleDTO scheduleDTO);
    
    @Mapping(target = "petIds", source = "pets")
    ScheduleDTO toDto(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule partialUpdate(ScheduleDTO scheduleDTO, @MappingTarget Schedule schedule);
}