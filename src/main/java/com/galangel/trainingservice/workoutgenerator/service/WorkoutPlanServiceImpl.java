package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService{
    private final FirebaseService firebaseService;
    private final Converter converter;
    private final WorkoutTemplateGenerator templateGenerator;

    public WorkoutPlanServiceImpl(FirebaseService firebaseService, Converter converter, WorkoutTemplateGenerator templateGenerator) {
        this.firebaseService = firebaseService;
        this.converter = converter;
        this.templateGenerator = templateGenerator;
    }

    @Override
    public WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        System.out.println("Received request: " + request);

        List<ExerciseDTO> exerciseDTOs = firebaseService.getExercises();
        WorkoutPlanEntity workoutPlanEntity = new WorkoutPlanEntity();

        TemplateType level = resolveLevel(request.getYearsOfExperience());
        int daysPerWeek = request.getDaysPerWeek();

        return generatePlan(exerciseDTOs, level, daysPerWeek);
    }


    private TemplateType resolveLevel(double years) {
        if (years < 1) return TemplateType.BEGINNER;
        if (years < 3) return TemplateType.INTERMEDIATE;
        return TemplateType.ADVANCED;
    }

    private WorkoutPlanEntity generatePlan(List<ExerciseDTO> exercises, TemplateType level, int daysPerWeek) {
        WorkoutPlanEntity workoutPlan = new WorkoutPlanEntity();
        Map<Integer, List<MuscleGroup>> template = templateGenerator.getTemplate(level, daysPerWeek);

        for (int day = 0; day < 7; day++) {
            if (!template.containsKey(day)) {
                workoutPlan.addDayPlan(null);
                continue;
            }

            List<MuscleGroup> targetMuscles = template.get(day);

            List<ExerciseEntity> filtered = exercises.stream()
                    .filter(dto -> {
                        try {
                            MuscleGroup group = MuscleGroup.valueOf(dto.getMainMuscle().toUpperCase().trim());
                            return targetMuscles.contains(group);
                        } catch (IllegalArgumentException e) {
                            return false;
                        }
                    })
                    .map(dto -> converter.convertToEntity(dto, 3, 12, 60))
                    .toList();

            workoutPlan.addDayPlan(new DayPlanEntity(filtered));
        }

        return workoutPlan;
    }



}
