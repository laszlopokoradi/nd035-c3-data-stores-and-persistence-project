package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.mapper.user.*;
import java.time.*;
import java.util.*;
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

    public UserController(UserService userService, CustomerMapper customerMapper) {
        this.userService = userService;
        this.customerMapper = customerMapper;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        if (customerDTO.getPetIds() == null) {
            customerDTO.setPetIds(new ArrayList<>());
        }

        Customer createdCustomer = this.userService.create(customerMapper.toEntity(customerDTO));

        return CustomerDTO.fromEntity(createdCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = this.userService.getAllCustomers();

        return
                customers.stream().map(customerMapper::toDto).toList();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable UUID petId) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee createdEmployee = this.userService.create(employeeDTO);

        return EmployeeDTO.fromEntity(createdEmployee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable UUID employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable,
            @PathVariable UUID employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}
