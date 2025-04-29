package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;

import java.util.List;

/**
 * Selects suitable exercises for a given muscle group, avoiding duplicates within the workout plan.
 */

public interface WorkoutPlanGenerator {
    WorkoutPlanEntity generate(WorkoutRequestDTO request, List<ExerciseDTO> exercises);
}
