package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
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

    public UUID map(Customer customer) {
        if (customer == null) {
            return null;
        }
        return customer.getId();
    }
}
