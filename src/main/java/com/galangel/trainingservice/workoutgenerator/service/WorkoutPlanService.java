package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.DayPlanEntity;

public interface WorkoutPlanService {
    DayPlanEntity generateWorkoutPlan(WorkoutRequestDTO request);
}
