package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.*;
import org.springframework.data.jpa.repository.*;

import java.time.*;
import java.util.*;
import org.springframework.stereotype.*;

@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {

    @Query("SELECT e FROM Employee e JOIN e.daysAvailable d JOIN e.skills s WHERE d = :dayOfWeek AND s IN :skills")
    List<Employee> findAllByDaysAvailableAndSkills(DayOfWeek dayOfWeek, Set<Activity> skills);
}