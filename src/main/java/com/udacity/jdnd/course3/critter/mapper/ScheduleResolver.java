package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.pet.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Component
public class ScheduleResolver {

    Set<Pet> resolvePetIds(List<UUID> petIds) {
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

    List<UUID> resolvePets(Set<Pet> pets) {
        if (pets == null) {
            return null;
        }

        return pets.stream().map(Pet::getId).toList();
    }
}
