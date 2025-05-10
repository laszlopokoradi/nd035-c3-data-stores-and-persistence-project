package com.udacity.jdnd.course3.critter.controller;


import com.udacity.jdnd.course3.critter.dto.*;
import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.mapper.*;
import com.udacity.jdnd.course3.critter.service.*;
import jakarta.persistence.*;
import jakarta.validation.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/activities")
public class ActivityController {

    private final ActivityService activityService;
    private final ActivityMapper activityMapper;

    public ActivityController(ActivityService activityService, ActivityMapper activityMapper) {
        this.activityService = activityService;
        this.activityMapper = activityMapper;
    }

    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = this.activityService.getAllActivities();

        return activities.stream()
                         .map(activityMapper::toDTO)
                         .toList();
    }

    @GetMapping("/{id}")
    public ActivityDTO getActivity(@PathVariable Long id) {
        Optional<Activity> activity = this.activityService.getActivity(id);

        return activity
                .map(activityMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("No activity (id: %s) found".formatted(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActivityDTO createActivity(@Valid @RequestBody ActivityDTO activityDTO) {
        Activity createdActivity = activityService.saveActivity(
                activityMapper.toEntity(activityDTO));

        return activityMapper.toDTO(createdActivity);
    }

    @PutMapping("/{id}")
    public ActivityDTO updateActivity(@PathVariable Long id,
            @Valid @RequestBody ActivityDTO activityDTO) {
        if (activityDTO.getId() != null && !activityDTO.getId().equals(id)) {
            throw new IllegalArgumentException(
                    "Activity id in path (%s) does not match id in body (%s)".formatted(id, activityDTO.getId()));
        }

        Optional<Activity> activity = this.activityService.getActivity(id);

        Activity a;
        if (activity.isPresent()) {
            a = activity.get();
            activityMapper.updateFromDTO(activityDTO, a);
        } else {
            throw new EntityNotFoundException("No activity (id: %s) found".formatted(id));
        }

        return activityMapper.toDTO(a);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
    }
}
