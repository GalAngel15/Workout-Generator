package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Default implementation of the WorkoutPlanGenerator.
 * <p>
 * Builds a full 7-day workout plan using a weekly muscle group template,
 * randomly selected exercises, and fixed training parameters (sets, reps, rest).
 * <p>
 * The plan varies based on user experience level and preferred training days per week.
 */
@Component
public class DefaultWorkoutPlanGenerator implements WorkoutPlanGenerator {

    private final WorkoutTemplateGenerator templateGenerator;
    private final ExerciseSelectionStrategy exerciseSelectionStrategy;
    private final Converter converter;
    private final ExercisePicker exercisePicker;
    private final RepSchemeSelector repSchemeSelector;

    public DefaultWorkoutPlanGenerator(
            WorkoutTemplateGenerator templateGenerator,
            ExerciseSelectionStrategy exerciseSelectionStrategy,
            Converter converter, ExercisePicker exercisePicker, RepSchemeSelector repSchemeSelector
    ) {
        this.templateGenerator = templateGenerator;
        this.exerciseSelectionStrategy = exerciseSelectionStrategy;
        this.converter = converter;
        this.exercisePicker = exercisePicker;
        this.repSchemeSelector = repSchemeSelector;
    }

    /**
     * Generates a personalized weekly workout plan for a user.
     *
     * @param request   User preferences including goal, experience level, and training frequency.
     * @param exercises List of all available exercises to choose from.
     * @return A structured 7-day workout plan tailored to the user's profile.
     */
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

                RepScheme scheme = repSchemeSelector.getSchemeFor(request.getGoal());

                // Convert selected exercises to entities with default values
                selected.stream()
                        .map(dto -> converter.convertToEntity(dto, scheme.sets(), scheme.reps(), scheme.rest()))
                        .forEach(dayExercises::add);
            }

            // Add the final day plan to the workout, with its number and exercises
            workoutPlan.addDayPlan(new DayPlanEntity(day, dayExercises));
        }

        // Return the complete weekly workout plan
        return workoutPlan;
    }

    /**
     * Maps user's training experience (in years) to a predefined TemplateType.
     *
     * @param years Number of years the user has been training.
     * @return The corresponding TemplateType (BEGINNER, INTERMEDIATE, ADVANCED).
     */
    private TemplateType resolveLevel(double years) {
        if (years < 1) return TemplateType.BEGINNER;
        if (years < 3) return TemplateType.INTERMEDIATE;
        return TemplateType.ADVANCED;
    }
}
