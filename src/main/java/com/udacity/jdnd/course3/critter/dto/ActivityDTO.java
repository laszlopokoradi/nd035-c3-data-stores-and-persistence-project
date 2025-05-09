package com.udacity.jdnd.course3.critter.dto;

import jakarta.validation.constraints.*;
import java.io.*;
import java.util.*;

/**
 * DTO for {@link com.udacity.jdnd.course3.critter.entity.Activity}
 */
public class ActivityDTO implements Serializable {

    @NotBlank
    private final String name;

    public ActivityDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActivityDTO entity = (ActivityDTO) o;
        return Objects.equals(this.name, entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ")";
    }
}