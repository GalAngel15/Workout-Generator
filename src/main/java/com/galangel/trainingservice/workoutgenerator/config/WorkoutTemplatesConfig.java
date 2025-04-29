package com.galangel.trainingservice.workoutgenerator.config;

import com.galangel.trainingservice.workoutgenerator.model.MuscleGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "templates")
public class WorkoutTemplatesConfig {
    private Map<String, Map<Integer, Map<Integer, List<MuscleGroup>>>> templates;
}
