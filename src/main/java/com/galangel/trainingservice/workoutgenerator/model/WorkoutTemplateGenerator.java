package com.galangel.trainingservice.workoutgenerator.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides predefined workout splits (templates) for each experience level and weekly frequency.
 */
@Component
public class WorkoutTemplateGenerator {

    public Map<Integer, List<MuscleGroup>> getTemplate(TemplateType type, int daysPerWeek) {
        return switch (type) {
            case BEGINNER -> getBeginnerTemplate(daysPerWeek);
            case INTERMEDIATE -> getIntermediateTemplate(daysPerWeek);
            case ADVANCED -> getAdvancedTemplate(daysPerWeek);
        };
    }

    private Map<Integer, List<MuscleGroup>> getBeginnerTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
        if (daysPerWeek == 2) {
            template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.LEGS));
            template.put(2, List.of(MuscleGroup.SHOULDERS, MuscleGroup.CORE, MuscleGroup.BICEPS, MuscleGroup.TRICEPS));
        } else { // 3 days
            template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS));
            template.put(2, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
            template.put(4, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS));
        }
        return template;
    }

    private Map<Integer, List<MuscleGroup>> getIntermediateTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
        if (daysPerWeek == 4) { // 4 days
            template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.TRICEPS));
            template.put(1, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS));
            template.put(3, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
            template.put(5, List.of(MuscleGroup.SHOULDERS, MuscleGroup.CORE));
        } else { // 5 days
            template.put(0, List.of(MuscleGroup.CHEST));
            template.put(1, List.of(MuscleGroup.BACK));
            template.put(2, List.of(MuscleGroup.LEGS));
            template.put(4, List.of(MuscleGroup.SHOULDERS));
            template.put(5, List.of(MuscleGroup.BICEPS, MuscleGroup.TRICEPS, MuscleGroup.CORE));
        }
        return template;
    }

    private Map<Integer, List<MuscleGroup>> getAdvancedTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
        if (daysPerWeek == 5) { // 5 days
            template.put(0, List.of(MuscleGroup.CHEST));
            template.put(1, List.of(MuscleGroup.BACK));
            template.put(2, List.of(MuscleGroup.LEGS));
            template.put(3, List.of(MuscleGroup.SHOULDERS));
            template.put(5, List.of(MuscleGroup.FULL_BODY));
        }
        return template;
    }
}
