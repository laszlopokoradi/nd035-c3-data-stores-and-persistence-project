package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;

import java.time.*;
import java.util.*;

import com.udacity.jdnd.course3.critter.service.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and
 * customer controllers would be fine too, though that is not part of the required scope for this
 * class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CustomerMapper customerMapper;
    private final EmployeeMapper employeeMapper;

    public UserController(UserService userService, CustomerMapper customerMapper, EmployeeMapper employeeMapper) {
        this.userService = userService;
        this.customerMapper = customerMapper;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = this.userService.create(customerMapper.toEntity(customerDTO));

        return this.customerMapper.toDto(createdCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = this.userService.getAllCustomers();

        return
                customers.stream().map(customerMapper::toDto).toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable UUID petId) {
        Optional<Customer> customer = this.userService.getOwnerByPet(petId);

        return customer
                .map(customerMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No owner of pet (id: %s) found".formatted(petId)));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = this.employeeMapper.toEntity(employeeDTO);

        Employee createdEmployee = this.userService.create(employee);

        return employeeMapper.toDto(createdEmployee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> employee = this.userService.getEmployee(employeeId);

        return employee
                .map(employeeMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("No employee (id: %s) found".formatted(employeeId)));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable,
            @PathVariable UUID employeeId) {
        Optional<Employee> employee = this.userService.getEmployee(employeeId);

        if (employee.isPresent()) {
            Employee e = employee.get();
            e.setDaysAvailable(daysAvailable);
        } else {
            throw new EntityNotFoundException("No employee (id: %s) found".formatted(employeeId));
        }
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO requestDTO) {
        List<Employee> employees = this.userService.findAvailableEmployees(requestDTO.getDate(), requestDTO.getSkills());

        return employees.stream()
                .map(employeeMapper::toDto)
                .toList();
    }

}
