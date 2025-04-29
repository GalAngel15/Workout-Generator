package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;

import java.util.List;

/**
 * Selects suitable exercises for a given muscle group, avoiding duplicates within the workout plan.
 */

public interface ExercisePicker {
    /**
     * Selects a list of exercises from the provided pool based on the given muscle group and requested count.
     * <p>
     * This method filters the available exercises to include only those that match the specified muscle group
     * and that haven't been picked before (to avoid duplicates within the same workout plan). Then it shuffles
     * the list and returns up to {@code count} exercises.
     *
     * @param allExercises the complete list of available exercises, typically retrieved from Firebase.
     * @param targetGroup  the target muscle group for which exercises should be selected.
     * @param count        the desired number of exercises to return for the given muscle group.
     * @return a list of selected exercises, filtered and shuffled, with a maximum size of {@code count}.
     */

    List<ExerciseDTO> pickExercises(List<ExerciseDTO> allExercises, MuscleGroup targetGroup, int count);
}
