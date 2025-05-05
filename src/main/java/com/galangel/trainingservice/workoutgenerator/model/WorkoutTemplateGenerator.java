package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.enums.MuscleGroup;
import com.galangel.trainingservice.workoutgenerator.enums.WorkoutSplitType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides predefined workout splits (templates) for each experience level and weekly frequency.
 */
@Component
public class WorkoutTemplateGenerator {

//    public Map<Integer, List<MuscleGroup>> getTemplate(ExerciserLevel type, int daysPerWeek) {
//        return switch (type) {
//            case BEGINNER -> getBeginnerTemplate(daysPerWeek);
//            case INTERMEDIATE -> getIntermediateTemplate(daysPerWeek);
//            case ADVANCED -> getAdvancedTemplate(daysPerWeek);
//        };
//
//        return switch (daysPerWeek) {
//            case 1 -> getFullBodyTemplate(daysPerWeek);
//            case 2 -> getABTemplate(daysPerWeek);
//            case 3 -> getABCTemplate(daysPerWeek);
//            case 4 -> getUpperLowerTemplate(daysPerWeek);
//            default -> getPPLTemplate(daysPerWeek);
//        };
//    }
    public Map<Integer, List<MuscleGroup>> getTemplate(WorkoutSplitType splitType, int daysPerWeek) {
        return switch (splitType) {
            case FULL_BODY -> getFullBodyTemplate();
            case AB -> getABTemplate();
            case ABC -> getABCTemplate();
            case UPPER_LOWER -> getUpperLowerTemplate();
            case PPL -> getPPLTemplate(daysPerWeek);
            case BRO_SPLIT -> getBroSplitTemplate(daysPerWeek);
        };
    }
    private Map<Integer, List<MuscleGroup>> getFullBodyTemplate() {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
            template.put(0, List.of(
                    MuscleGroup.CHEST,
                    MuscleGroup.BACK,
                    MuscleGroup.LEGS,
                    MuscleGroup.SHOULDERS,
                    MuscleGroup.BICEPS,
                    MuscleGroup.TRICEPS,
                    MuscleGroup.CORE
            ));
        return template;
    }

    private Map<Integer, List<MuscleGroup>> getABTemplate() {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
                template.put(0, List.of(
                        MuscleGroup.CHEST,
                        MuscleGroup.SHOULDERS,
                        MuscleGroup.TRICEPS
                ));
                template.put(1, List.of(
                        MuscleGroup.BACK,
                        MuscleGroup.BICEPS,
                        MuscleGroup.LEGS,
                        MuscleGroup.CORE
                ));
        return template;
    }

    private Map<Integer, List<MuscleGroup>> getABCTemplate() {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
                template.put(0, List.of(
                        MuscleGroup.CHEST,
                        MuscleGroup.TRICEPS
                ));
                template.put(1, List.of(
                        MuscleGroup.BACK,
                        MuscleGroup.BICEPS
                ));
                template.put(2, List.of(
                        MuscleGroup.LEGS,
                        MuscleGroup.SHOULDERS,
                        MuscleGroup.CORE
                ));

        return template;
    }

    private Map<Integer, List<MuscleGroup>> getUpperLowerTemplate() {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
                template.put(0, List.of(
                        MuscleGroup.CHEST,
                        MuscleGroup.BACK,
                        MuscleGroup.SHOULDERS,
                        MuscleGroup.BICEPS,
                        MuscleGroup.TRICEPS
                ));
                template.put(1, List.of(
                        MuscleGroup.LEGS,
                        MuscleGroup.CORE
                ));

        return template;
    }

    private Map<Integer, List<MuscleGroup>> getPPLTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
                template.put(0, List.of(
                        MuscleGroup.CHEST,
                        MuscleGroup.SHOULDERS,
                        MuscleGroup.TRICEPS
                ));
                template.put(1, List.of(
                        MuscleGroup.BACK,
                        MuscleGroup.BICEPS
                ));
                template.put(2, List.of(
                        MuscleGroup.LEGS,
                        MuscleGroup.CORE
                ));
        return template;
    }

    private Map<Integer, List<MuscleGroup>> getBroSplitTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();

        template.put(0, List.of(MuscleGroup.CHEST));
        template.put(1, List.of(MuscleGroup.BACK));
        template.put(2, List.of(MuscleGroup.LEGS));
        template.put(3, List.of(MuscleGroup.SHOULDERS));
        template.put(4, List.of(MuscleGroup.BICEPS, MuscleGroup.TRICEPS));

        if (daysPerWeek >= 6) {
            template.put(5, List.of(MuscleGroup.CORE, MuscleGroup.GLUTES)); // יום בונוס
        }

        return template;
    }

    /*
    private Map<Integer, List<MuscleGroup>> getBeginnerTemplate(int daysPerWeek) {
        Map<Integer, List<MuscleGroup>> template = new HashMap<>();
        if (daysPerWeek <= 2) {
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
        if (daysPerWeek <= 3) { // 4 days
            template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.TRICEPS));
            template.put(2, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS, MuscleGroup.SHOULDERS));
            template.put(4, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
        }else if (daysPerWeek == 4) { // 4 days
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
        if (daysPerWeek <= 4) {
            template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.TRICEPS));
            template.put(2, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS, MuscleGroup.SHOULDERS));
            template.put(4, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
        }
        else { // 5 days or more
            template.put(0, List.of(MuscleGroup.CHEST));
            template.put(1, List.of(MuscleGroup.BACK));
            template.put(2, List.of(MuscleGroup.LEGS));
            template.put(3, List.of(MuscleGroup.SHOULDERS));
            template.put(5, List.of(MuscleGroup.BICEPS, MuscleGroup.TRICEPS, MuscleGroup.CORE));
        }
        return template;
    }

     */
}
