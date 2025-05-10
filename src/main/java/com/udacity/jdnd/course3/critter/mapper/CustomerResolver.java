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

    public Customer resolveCustomerId(UUID customerId) {
        if (customerId == null) {
            return null;
        }
        return customerRepository.findById(customerId)
                                 .orElseThrow(() -> new EntityNotFoundException(
                                         "Customer not found with id: " + customerId));
    }

    public UUID resolveCustomer(Customer customer) {
        if (customer == null) {
            return null;
        }
        return customer.getId();
    }
}
