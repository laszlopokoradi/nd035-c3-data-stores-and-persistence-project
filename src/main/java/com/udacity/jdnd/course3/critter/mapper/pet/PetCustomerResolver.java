package com.udacity.jdnd.course3.critter.mapper.pet;


import com.udacity.jdnd.course3.critter.user.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class PetCustomerResolver {
    private final CustomerRepository customerRepository;

    public PetCustomerResolver(CustomerRepository customerRepository) {
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
