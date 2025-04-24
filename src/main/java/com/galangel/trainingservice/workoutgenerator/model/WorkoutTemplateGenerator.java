package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        switch (daysPerWeek) {
            case 2 -> {
                // גוף מלא פעמיים – גיוון קל בין הימים
                template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.BACK, MuscleGroup.LEGS));
                template.put(2, List.of(MuscleGroup.SHOULDERS, MuscleGroup.CORE, MuscleGroup.BICEPS, MuscleGroup.TRICEPS));
            }
            case 3 -> {
                // Split קלאסי – דחיפה, רגליים, משיכה
                template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS));
                template.put(2, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
                template.put(4, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS));
            }
            case 4 -> {
                // Upper/Lower X2
                template.put(0, List.of(MuscleGroup.CHEST, MuscleGroup.SHOULDERS, MuscleGroup.TRICEPS));
                template.put(1, List.of(MuscleGroup.LEGS, MuscleGroup.CORE));
                template.put(3, List.of(MuscleGroup.BACK, MuscleGroup.BICEPS));
                template.put(4, List.of(MuscleGroup.LEGS, MuscleGroup.CORE)); // חיזוק רגליים, ליבה חוזרת
            }
            default -> {
                // ברירת מחדל – אם קלט שגוי או יותר מ־4
                template.put(0, List.of(MuscleGroup.FULL_BODY));
            }
        }

        return template;
    }


    private Map<Integer, List<MuscleGroup>> getIntermediateTemplate(int daysPerWeek) {
        // לפיתוח בהמשך
        return new HashMap<>();
    }

    private Map<Integer, List<MuscleGroup>> getAdvancedTemplate(int daysPerWeek) {
        // לפיתוח בהמשך
        return new HashMap<>();
    }

}
