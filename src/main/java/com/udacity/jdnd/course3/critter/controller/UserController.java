package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;
import com.udacity.jdnd.course3.critter.service.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import java.time.*;
import java.util.*;
import org.springframework.web.bind.annotation.*;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers would
 * be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CustomerMapper customerMapper;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRequestMapper requestMapper;

    public UserController(UserService userService, CustomerMapper customerMapper, EmployeeMapper employeeMapper,
            EmployeeRequestMapper requestMapper) {
        this.userService = userService;
        this.customerMapper = customerMapper;
        this.employeeMapper = employeeMapper;
        this.requestMapper = requestMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer createdCustomer = this.userService.create(customerMapper.toEntity(customerDTO));

        return this.customerMapper.toDTO(createdCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = this.userService.getAllCustomers();

        return
                customers.stream().map(customerMapper::toDTO).toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable UUID petId) {
        Optional<Customer> customer = this.userService.getOwnerByPet(petId);

        return customer
                .map(customerMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("No owner of pet (id: %s) found".formatted(petId)));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = this.employeeMapper.toEntity(employeeDTO);

        Employee createdEmployee = this.userService.create(employee);

        return employeeMapper.toDTO(createdEmployee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable UUID employeeId) {
        Optional<Employee> employee = this.userService.getEmployee(employeeId);

        return employee
                .map(employeeMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("No employee (id: %s) found".formatted(employeeId)));
    }

    @PutMapping("/employee/{employeeId}")
    public EmployeeDTO setAvailability(@PathVariable UUID employeeId,
            @RequestBody Set<DayOfWeek> daysAvailable) {
        Optional<Employee> employee = this.userService.getEmployee(employeeId);

        Employee e;
        if (employee.isPresent()) {
            e = employee.get();
            e.setDaysAvailable(new HashSet<>(daysAvailable));
            this.userService.updateEmployee(e);
        } else {
            throw new EntityNotFoundException("No employee (id: %s) found".formatted(employeeId));
        }

        return employeeMapper.toDTO(e);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO requestDTO) {
        EmployeeRequest request = this.requestMapper.toEntity(requestDTO);

        List<Employee> employees = this.userService.findAvailableEmployees(request);

        return employees.stream()
                        .map(employeeMapper::toDTO)
                        .toList();
    }

}
