package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActivityMapper {
    @Mapping(target = "id", ignore = true)
    Activity toEntity(ActivityDTO activityDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Activity updateFromDTO(ActivityDTO dto, @MappingTarget Activity pet);

    ActivityDTO toDTO(Activity activity);
}
