package com.udacity.jdnd.course3.critter.user;


import com.udacity.jdnd.course3.critter.mapper.user.*;
import org.springframework.stereotype.*;

import java.util.*;


@Service
public class UserService {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
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

    protected Optional<Customer> getOwnerByPet(UUID petId) {
        return this.customerRepository.findByPetId(petId);
    }

    protected Optional<Employee> getEmployee(UUID employeeId) {
        return this.employeeRepository.findById(employeeId);
    }
}
