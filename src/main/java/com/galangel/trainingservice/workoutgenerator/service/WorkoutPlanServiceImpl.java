package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.DayPlanEntity;
import com.galangel.trainingservice.workoutgenerator.model.ExerciseEntity;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;
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
    public WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        System.out.println("Received request: " + request);
        List<ExerciseDTO> exerciseDTOs = firebaseService.getExercises();
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();
        for (int i = 0; i < 7; i++) {
            if(i%2==1){
                List<ExerciseEntity> exerciseEntities = exerciseDTOs.stream()
                        .map(dto -> converter.convertToEntity(dto, 3, 12, 60))
                        .toList();
                workoutPlanEntity.addDayPlan(new DayPlanEntity(exerciseEntities));
            }else{
                workoutPlanEntity.addDayPlan(null);
            }
        }
        return workoutPlanEntity;
    }
}
