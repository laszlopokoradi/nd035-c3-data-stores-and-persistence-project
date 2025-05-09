package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.entity.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Component
public class ScheduleResolver {

    Set<Pet> resolvePetIds(List<UUID> petIds) {
        if (petIds == null) {
            return Collections.emptySet();
        }

        return petIds.stream().map(
                petId -> {
                    Pet pet = new Pet();
                    pet.setId(petId);
                    return pet;
                }
        ).collect(Collectors.toSet());
    }

    List<UUID> resolvePets(Set<Pet> pets) {
        if (pets == null) {
            return Collections.emptyList();
        }

        return pets.stream().map(Pet::getId).toList();
    }
}
