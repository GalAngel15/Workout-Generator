package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.enums.ExerciserLevel;
import com.galangel.trainingservice.workoutgenerator.enums.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Determines how many exercises to assign per muscle group based on user level (template type).
 */
@Component
public class ExerciseSelectionStrategy {

    private final Map<ExerciserLevel, Map<MuscleGroup, Integer>> config = new HashMap<>();

    public ExerciseSelectionStrategy() {
        config.put(ExerciserLevel.BEGINNER, beginnerConfig());
        config.put(ExerciserLevel.INTERMEDIATE, intermediateConfig());
        config.put(ExerciserLevel.ADVANCED, advancedConfig());
    }

    public int getRecommendedCount(ExerciserLevel level, MuscleGroup group) {
        return config.getOrDefault(level, beginnerConfig())
                .getOrDefault(group, 1);
    }

    private Map<MuscleGroup, Integer> beginnerConfig() {
        Map<MuscleGroup, Integer> map = new HashMap<>();
        map.put(MuscleGroup.LEGS, 3);
        map.put(MuscleGroup.CHEST, 2);
        map.put(MuscleGroup.BACK, 2);
        map.put(MuscleGroup.SHOULDERS, 1);
        map.put(MuscleGroup.CORE, 1);
        map.put(MuscleGroup.TRICEPS, 1);
        map.put(MuscleGroup.BICEPS, 1);
        return map;
    }

    private Map<MuscleGroup, Integer> intermediateConfig() {
        Map<MuscleGroup, Integer> map = beginnerConfig();
        map.put(MuscleGroup.SHOULDERS, 2);
        map.put(MuscleGroup.CORE, 2);
        map.put(MuscleGroup.TRICEPS, 2);
        map.put(MuscleGroup.BICEPS, 2);
        return map;
    }

    private Map<MuscleGroup, Integer> advancedConfig() {
        Map<MuscleGroup, Integer> map = intermediateConfig();
        map.put(MuscleGroup.CHEST, 3);
        map.put(MuscleGroup.BACK, 3);
        map.put(MuscleGroup.LEGS, 4);
        return map;
    }
}