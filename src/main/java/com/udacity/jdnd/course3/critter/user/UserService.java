package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.mapper.user.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class UserService {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private CustomerMapper customerMapper;

    public UserService(CustomerRepository customerRepository, CustomerMapper customerPetMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerPetMapper;
    }

    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }
}
