package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.dto.Goal;

import java.util.ArrayList;
import java.util.List;

public class WorkoutPlanEntity {
    private Goal goal;
    private List<DayPlanEntity> days;

    public WorkoutPlanEntity() {
        days=new ArrayList<>(7);
    }

}
