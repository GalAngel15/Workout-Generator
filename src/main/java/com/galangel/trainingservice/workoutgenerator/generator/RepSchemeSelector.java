package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.dto.Goal;
import org.springframework.stereotype.Component;

/**
 * Returns a recommended set/reps/rest scheme based on the user's goal.
 */
@Component
public class RepSchemeSelector {

    public RepScheme getSchemeFor(Goal goal) {
        return switch (goal) {
            case LOSE_WEIGHT -> new RepScheme(3, 15, 30);    // higher reps, low rest
            case MUSCLE_GAIN -> new RepScheme(4, 10, 60);              // hypertrophy range
            case STRENGTH -> new RepScheme(5, 5, 120);                 // power focus
            default -> new RepScheme(3, 12, 60);                       // default balanced
        };
    }
}