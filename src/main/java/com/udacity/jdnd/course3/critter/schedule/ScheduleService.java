package com.udacity.jdnd.course3.critter.schedule;


import org.springframework.stereotype.*;

import java.util.*;


@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                                 .orElse(null);
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return this.scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesForPet(UUID petId) {
        return this.scheduleRepository.findAllByPetId(petId);
    }

    public List<Schedule> getSchedulesForEmployee(UUID employeeId) {
        return this.scheduleRepository.findAllByEmployeeId(employeeId);
    }

    protected List<Schedule> getSchedulesForCustomer(UUID customerId) {
        return this.scheduleRepository.findAllByCustomerId(customerId);
    }
}
