package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;
import com.udacity.jdnd.course3.critter.service.*;
import jakarta.validation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final ScheduleMapper scheduleMapper;

    public ScheduleController(ScheduleService scheduleService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.scheduleMapper = scheduleMapper;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO) {
        Schedule createdSchedule = this.scheduleService.createSchedule(scheduleMapper.toEntity(scheduleDTO));

        return scheduleMapper.toDTO(createdSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = this.scheduleService.getAllSchedules();

        return schedules.stream()
                        .map(scheduleMapper::toDTO)
                        .toList();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable UUID petId) {
        List<Schedule> schedules = this.scheduleService.getSchedulesForPet(petId);

        return schedules.stream()
                        .map(scheduleMapper::toDTO)
                        .toList();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable UUID employeeId) {
        List<Schedule> schedules = this.scheduleService.getSchedulesForEmployee(employeeId);

        return schedules.stream()
                        .map(scheduleMapper::toDTO)
                        .toList();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable UUID customerId) {
        List<Schedule> schedules = this.scheduleService.getSchedulesForCustomer(customerId);

        return schedules.stream()
                        .map(scheduleMapper::toDTO)
                        .toList();
    }
}
