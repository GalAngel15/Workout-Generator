package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;

import java.util.List;

public interface ExercisePicker {
    List<ExerciseDTO> pickExercises(List<ExerciseDTO> allExercises, MuscleGroup targetGroup, int count);
}
