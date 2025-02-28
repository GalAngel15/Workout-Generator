package com.galangel.trainingservice.workoutgenerator.components;

import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.model.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class Converter {
    public ExerciseEntity convertToEntity(ExerciseDTO dto, int sets, int reps, int rest) {
        return new ExerciseEntity(dto.getName(), dto.getMainMuscle(), dto.getImageUrl(), sets, reps, rest);
    }
}
