package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DefaultWorkoutPlanGenerator implements WorkoutPlanGenerator {

    private final WorkoutTemplateGenerator templateGenerator;
    private final ExerciseSelectionStrategy exerciseSelectionStrategy;
    private final Converter converter;
    private final ExercisePicker exercisePicker;


    public DefaultWorkoutPlanGenerator(
            WorkoutTemplateGenerator templateGenerator,
            ExerciseSelectionStrategy exerciseSelectionStrategy,
            Converter converter, ExercisePicker exercisePicker
    ) {
        this.templateGenerator = templateGenerator;
        this.exerciseSelectionStrategy = exerciseSelectionStrategy;
        this.converter = converter;
        this.exercisePicker = exercisePicker;
    }

    @Override
    public WorkoutPlanEntity generate(WorkoutRequestDTO request, List<ExerciseDTO> exercises) {
        // Determine training level based on user's experience
        TemplateType level = resolveLevel(request.getYearsOfExperience());

        // Get how many days per week the user wants to train
        int daysPerWeek = request.getDaysPerWeek();

        // Retrieve the weekly muscle group split template
        Map<Integer, List<MuscleGroup>> template = templateGenerator.getTemplate(level, daysPerWeek);

        // Initialize the workout plan and set user's goal
        WorkoutPlanEntity workoutPlan = new WorkoutPlanEntity();
        workoutPlan.setGoal(request.getGoal());

        // Loop over 7 days to fill each training day
        for (int day = 0; day < 7; day++) {
            // If there's no plan for this day, add empty day with its number
            if (!template.containsKey(day)) {
                workoutPlan.addDayPlan(new DayPlanEntity(day));
                continue;
            }

            // Prepare exercises for this specific day
            List<ExerciseEntity> dayExercises = new ArrayList<>();
            for (MuscleGroup group : template.get(day)) {
                // Loop over each muscle group planned for this day
                int count = exerciseSelectionStrategy.getRecommendedCount(level, group);

                // Pick random exercises for this muscle group
                List<ExerciseDTO> selected = exercisePicker.pickExercises(exercises, group, count);

                // Convert selected exercises to entities with default values
                selected.stream()
                        .map(dto -> converter.convertToEntity(dto, 3, 12, 60))
                        .forEach(dayExercises::add);

            }

            // Add the final day plan to the workout, with its number and exercises
            workoutPlan.addDayPlan(new DayPlanEntity(day, dayExercises));
        }

        // Return the complete weekly workout plan
        return workoutPlan;
    }

    private TemplateType resolveLevel(double years) {
        if (years < 1) return TemplateType.BEGINNER;
        if (years < 3) return TemplateType.INTERMEDIATE;
        return TemplateType.ADVANCED;
    }
}
