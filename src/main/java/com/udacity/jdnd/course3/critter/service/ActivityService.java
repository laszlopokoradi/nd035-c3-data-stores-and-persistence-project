package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import jakarta.transaction.*;
import java.util.*;
import org.springframework.stereotype.*;

@Service
@Transactional
public class ActivityService {

    private final ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> getActivity(Long id) {
        return activityRepository.findById(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
