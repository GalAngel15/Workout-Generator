package com.galangel.trainingservice.workoutgenerator.service;


import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.generator.WorkoutPlanGenerator;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService {
    private final FirebaseService firebaseService;
    private final WorkoutPlanGenerator planGenerator;

    public WorkoutPlanServiceImpl(FirebaseService firebaseService, WorkoutPlanGenerator planGenerator) {
        this.firebaseService = firebaseService;
        this.planGenerator = planGenerator;
    }

    @Override
    public WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        List<ExerciseDTO> exerciseDTOs = firebaseService.getExercises();
        return planGenerator.generate(request, exerciseDTOs);
    }
}
