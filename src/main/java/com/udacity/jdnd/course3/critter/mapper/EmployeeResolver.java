package com.udacity.jdnd.course3.critter.mapper;


import com.udacity.jdnd.course3.critter.user.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

import java.util.*;


@Component
public class EmployeeResolver {
    private final EmployeeRepository employeeRepository;

    public EmployeeResolver(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee resolveEmployeeId(UUID employeeId) {
        if (employeeId == null) {
            return null;
        }
        return employeeRepository.findById(employeeId)
                                 .orElseThrow(() -> new EntityNotFoundException(
                                         "Employee not found with id: " + employeeId));
    }

    public UUID resolveEmployee(Employee employee) {
        if (employee == null) {
            return null;
        }
        return employee.getId();
    }

    public List<Employee> resolveEmployeeIds(List<UUID> employeeIds) {
        if (employeeIds == null) {
            return null;
        }

        return employeeIds.stream()
                          .map(this::resolveEmployeeId)
                          .toList();
    }
}
