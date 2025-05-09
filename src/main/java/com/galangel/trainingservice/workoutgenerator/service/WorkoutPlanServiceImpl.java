package com.galangel.trainingservice.workoutgenerator.service;


import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.generator.WorkoutPlanGenerator;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Generating personalized workout plans.
 */
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
        System.out.println("generateWorkoutPlan WorkoutPlanService before Firebase ");
        List<ExerciseDTO> exerciseDTOs = firebaseService.getExercises();

        System.out.println("generateWorkoutPlan WorkoutPlanService after Firebase ");
        return planGenerator.generate(request, exerciseDTOs);
    }
}
