package com.udacity.jdnd.course3.critter.mapper;

import com.udacity.jdnd.course3.critter.entity.*;
import com.udacity.jdnd.course3.critter.repository.*;
import jakarta.persistence.*;
import org.springframework.stereotype.*;

@Component
public class ActivityResolver {

    private final ActivityRepository activityRepository;

    public ActivityResolver(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Activity resolveActivityId(Long activityId) {
        if (activityId == null) {
            return null;
        }
        return activityRepository.findById(activityId)
                                 .orElseThrow(() -> new EntityNotFoundException(
                                         "Activity not found with id: " + activityId));
    }

    public Long resolveActivity(Activity activity) {
        if (activity == null) {
            return null;
        }
        return activity.getId();
    }

}
