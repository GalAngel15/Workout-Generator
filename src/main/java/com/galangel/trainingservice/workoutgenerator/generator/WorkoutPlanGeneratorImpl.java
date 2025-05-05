package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.enums.ExerciserLevel;
import com.galangel.trainingservice.workoutgenerator.enums.MuscleGroup;
import com.galangel.trainingservice.workoutgenerator.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Default implementation of the WorkoutPlanGenerator.
 * <p>
 * Builds a full 7-day workout plan using a weekly muscle group template,
 * randomly selected exercises, and fixed training parameters (sets, reps, rest).
 * <p>
 * The plan varies based on user experience level and preferred training days per week.
 */
@Component
public class WorkoutPlanGeneratorImpl implements WorkoutPlanGenerator {

    private final WorkoutTemplateGenerator templateGenerator;
    private final ExerciseSelectionStrategy exerciseSelectionStrategy;
    private final Converter converter;
    private final ExercisePicker exercisePicker;
    private final RepSchemeSelector repSchemeSelector;

    public WorkoutPlanGeneratorImpl(
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
        ExerciserLevel level = resolveLevel(request.getYearsOfExperience());

        System.out.println("generateWorkoutPlan WorkoutPlanGeneratorImpl in generate");
        // Retrieve the weekly muscle group split template
        Map<Integer, List<MuscleGroup>> template = templateGenerator.getTemplate(request.getSplitType(), request.getDaysPerWeek());

        return buildWorkoutPlan(template, level, request, exercises);
    }

    /**
     * Maps user's training experience (in years) to a predefined TemplateType.
     *
     * @param years Number of years the user has been training.
     * @return The corresponding TemplateType (BEGINNER, INTERMEDIATE, ADVANCED).
     */
    private ExerciserLevel resolveLevel(double years) {
        if (years < 1) return ExerciserLevel.BEGINNER;
        if (years < 3) return ExerciserLevel.INTERMEDIATE;
        return ExerciserLevel.ADVANCED;
    }

    /**
     * Builds a full 7-day workout plan using the muscle group template and user preferences.
     *
     * @param template   the weekly split of muscle groups.
     * @param level      the training level.
     * @param request    the workout request.
     * @param exercises  the full list of available exercises.
     * @return a complete WorkoutPlanEntity.
     */
    private WorkoutPlanEntity buildWorkoutPlan(
            Map<Integer, List<MuscleGroup>> template,
            ExerciserLevel level,
            WorkoutRequestDTO request,
            List<ExerciseDTO> exercises
    ) {
        WorkoutPlanEntity plan = new WorkoutPlanEntity();
        plan.setGoal(request.getGoal());

        System.out.println("generateWorkoutPlan WorkoutPlanGeneratorImpl in buildWorkoutPlan");

        for (int day = 0; day < 7; day++) {
            List<MuscleGroup> groups = template.getOrDefault(day, List.of());
            DayPlanEntity dayPlan = buildDayPlan(day, groups, exercises, level, request);
            plan.addDayPlan(dayPlan);
        }

        return plan;
    }

    /**
     * Builds a plan for a specific day including selected exercises and rep schemes.
     *
     * @param day        the index of the day (0-6).
     * @param muscleGroups muscle groups to train that day.
     * @param exercises  the full exercise list.
     * @param level      the user training level.
     * @param request    the user request DTO.
     * @return a DayPlanEntity with all selected and configured exercises.
     */
    private DayPlanEntity buildDayPlan(
            int day,
            List<MuscleGroup> muscleGroups,
            List<ExerciseDTO> exercises,
            ExerciserLevel level,
            WorkoutRequestDTO request
    ) {
        List<ExerciseEntity> dayExercises = new ArrayList<>();
        Set<String> pickedExercises = new HashSet<>(); // prevent duplicates

        for (MuscleGroup group : muscleGroups) {
            int count = exerciseSelectionStrategy.getRecommendedCount(level, group);
            List<ExerciseDTO> selected = exercisePicker.pickExercises(exercises, group, count, pickedExercises);
            RepScheme scheme = repSchemeSelector.getSchemeFor(request.getGoal(), group);

            selected.stream()
                    .map(dto -> converter.convertToEntity(dto, scheme.sets(), scheme.reps(), scheme.rest()))
                    .forEach(dayExercises::add);
        }

        return new DayPlanEntity(day, dayExercises);
    }
}
