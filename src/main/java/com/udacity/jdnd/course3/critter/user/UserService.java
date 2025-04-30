package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.pet.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class UserService {
    private CustomerRepository customerRepository;

    public UserService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(CustomerDTO customerDTO) {

        Customer customer = new Customer()
                .setId(customerDTO.getId())
                .setName(customerDTO.getName())
                .setPhoneNumber(customerDTO.getPhoneNumber())
                .setNotes(customerDTO.getNotes());

        for (UUID petId : customerDTO.getPetIds()) {
            customer.getPets()
                    .add(new Pet().setId(petId));
        }

        return customerRepository.save(customer);
    }
}
