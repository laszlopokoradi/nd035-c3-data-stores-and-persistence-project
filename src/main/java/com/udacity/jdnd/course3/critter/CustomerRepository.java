package com.udacity.jdnd.course3.critter;


import com.udacity.jdnd.course3.critter.user.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;


public interface CustomerRepository
        extends JpaRepository<Customer, UUID> {
}