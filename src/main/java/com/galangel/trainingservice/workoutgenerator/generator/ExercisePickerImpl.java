package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.enums.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Selects suitable exercises for a given muscle group, avoiding duplicates within the workout plan.
 */
@Component
public class ExercisePickerImpl implements ExercisePicker {

    @Override
    public List<ExerciseDTO> pickExercises(List<ExerciseDTO> allExercises, MuscleGroup targetGroup, int count, Set<String> pickedExercises) {
        // Filter exercises by muscle group
        List<ExerciseDTO> matching = new ArrayList<>(
                allExercises.stream()
                .filter(e -> {
                    try {
                        return MuscleGroup.valueOf(e.getMainMuscle().toUpperCase().trim()) == targetGroup;
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .filter(e -> !pickedExercises.contains(e.getName())) // מניעת כפילויות
                .toList());

        Collections.shuffle(matching);

        List<ExerciseDTO> selected = matching.subList(0, Math.min(count, matching.size()));

        selected.forEach(e -> pickedExercises.add(e.getName()));

        return matching.subList(0, Math.min(count, matching.size()));
    }
}

