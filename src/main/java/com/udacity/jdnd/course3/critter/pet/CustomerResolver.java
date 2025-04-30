package com.udacity.jdnd.course3.critter.pet;


import com.udacity.jdnd.course3.critter.user.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class CustomerResolver {
    private final CustomerRepository customerRepository;

    public CustomerResolver(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer resolveCustomer(UUID ownerId) {
        if (ownerId == null) {
            return null;
        }
        return customerRepository.findById(ownerId)
                                 .orElseThrow(() -> new EntityNotFoundException(
                                         "Customer not found with id: " + ownerId));
    }
}
