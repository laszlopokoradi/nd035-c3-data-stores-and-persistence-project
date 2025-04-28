package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.*;
import org.springframework.stereotype.*;


@Service
public class UserService {
    private CustomerRepository customerRepository;

    public UserService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
