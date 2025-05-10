package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
