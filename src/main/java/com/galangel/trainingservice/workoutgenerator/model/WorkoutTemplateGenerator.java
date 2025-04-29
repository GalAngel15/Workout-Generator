package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.config.WorkoutTemplatesConfig;
import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WorkoutTemplateGenerator {

    private final WorkoutTemplatesConfig templatesConfig;

    public WorkoutTemplateGenerator(WorkoutTemplatesConfig templatesConfig) {
        this.templatesConfig = templatesConfig;
    }

    public Map<Integer, List<MuscleGroup>> getTemplate(TemplateType type, int daysPerWeek) {
        Map<String, Map<Integer, Map<Integer, List<MuscleGroup>>>> templates = templatesConfig.getTemplates();

        Map<Integer, Map<Integer, List<MuscleGroup>>> typeTemplates = templates.getOrDefault(type.name().toLowerCase(), Collections.emptyMap());

        return typeTemplates.getOrDefault(daysPerWeek, Collections.emptyMap());
    }

}
