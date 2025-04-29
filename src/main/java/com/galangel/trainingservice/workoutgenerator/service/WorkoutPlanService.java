package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;

/**
 * Generating personalized workout plans.
 */
public interface WorkoutPlanService {
    WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request);
}
