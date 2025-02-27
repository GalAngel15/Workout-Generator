package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.entity.WorkoutPlanEntity;

public interface WorkoutPlanService {
    WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request);
}
