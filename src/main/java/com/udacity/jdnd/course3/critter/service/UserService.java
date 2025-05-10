package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import java.util.*;
import org.springframework.stereotype.*;


@Service
public class UserService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public UserService(CustomerRepository customerRepository,
            EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Employee create(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Optional<Customer> getOwnerByPet(UUID petId) {
        return this.customerRepository.findByPetId(petId);
    }

    public Optional<Employee> getEmployee(UUID employeeId) {
        return this.employeeRepository.findById(employeeId);
    }

    public List<Employee> findAvailableEmployees(EmployeeRequest request) {
        List<Employee> employees = this.employeeRepository.findAll();

        return employees.stream()
                        .filter(employee -> employee.getDaysAvailable().contains(request.getDate().getDayOfWeek()))
                        .filter(employee -> employee.getSkills().containsAll(request.getSkills()))
                        .toList();
    }
}
