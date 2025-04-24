package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;

import java.util.List;

public interface WorkoutPlanGenerator {
    WorkoutPlanEntity generate(WorkoutRequestDTO request, List<ExerciseDTO> exercises);
}
