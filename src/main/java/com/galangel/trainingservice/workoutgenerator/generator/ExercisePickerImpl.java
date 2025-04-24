package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ExercisePickerImpl implements ExercisePicker {

    @Override
    public List<ExerciseDTO> pickExercises(List<ExerciseDTO> allExercises, MuscleGroup targetGroup, int count) {
        List<ExerciseDTO> matching = new ArrayList<>(
                allExercises.stream()
                .filter(e -> {
                    try {
                        return MuscleGroup.valueOf(e.getMainMuscle().toUpperCase().trim()) == targetGroup;
                    } catch (Exception ex) {
                        return false;
                    }
                })
                .toList());

        Collections.shuffle(matching);
        return matching.subList(0, Math.min(count, matching.size()));
    }
}

