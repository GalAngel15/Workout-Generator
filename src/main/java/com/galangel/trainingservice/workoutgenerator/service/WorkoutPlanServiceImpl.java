package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.DayPlanEntity;
import com.galangel.trainingservice.workoutgenerator.model.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService{
    private final FirebaseService firebaseService;
    private final Converter converter;

    public WorkoutPlanServiceImpl(FirebaseService firebaseService, Converter converter) {
        this.firebaseService = firebaseService;
        this.converter = converter;
    }

    @Override
    public DayPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        List<ExerciseDTO> exerciseDTOs = firebaseService.getExercises();

        List<ExerciseEntity> exerciseEntities = exerciseDTOs.stream()
                .map(dto -> converter.convertToEntity(dto, 3, 12, 60))
                .toList();

        return new DayPlanEntity(exerciseEntities);
    }
}
