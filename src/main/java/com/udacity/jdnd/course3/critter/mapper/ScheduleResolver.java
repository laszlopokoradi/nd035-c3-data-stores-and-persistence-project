package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.pet.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Component
public class ScheduleResolver {

    Set<Pet> map(List<UUID> petIds) {
        if (petIds == null) {
            return null;
        }

        return petIds.stream().map(
                petId -> {
                    Pet pet = new Pet();
                    pet.setId(petId);
                    return pet;
                }
        ).collect(Collectors.toSet());
    }
}
