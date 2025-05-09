package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface ScheduleRepository
        extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.id = :petId")
    List<Schedule> findAllByPetId(UUID petId);

    @Query("SELECT s FROM Schedule s JOIN s.employees e WHERE e.id = :employeeId")
    List<Schedule> findAllByEmployeeId(UUID employeeId);

    @Query("SELECT s FROM Schedule s JOIN s.pets p WHERE p.owner.id = :customerId")
    List<Schedule> findAllByCustomerId(UUID customerId);
}
