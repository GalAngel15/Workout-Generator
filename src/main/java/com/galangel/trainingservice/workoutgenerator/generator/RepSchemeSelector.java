package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.enums.Goal;
import com.galangel.trainingservice.workoutgenerator.enums.MuscleGroup;
import org.springframework.stereotype.Component;

/**
 * Returns a recommended set/reps/rest scheme based on the user's goal and target muscle group.
 */
@Component
public class RepSchemeSelector {

    public RepScheme getSchemeFor(Goal goal, MuscleGroup muscleGroup) {
        switch (goal) {
            case LOSE_WEIGHT -> {
                if (muscleGroup == MuscleGroup.CORE || muscleGroup == MuscleGroup.CARDIO) {
                    return new RepScheme(3, 20, 30); // יותר חזרות לאזורים מתפרצים
                }
                return new RepScheme(3, 15, 30);
            }
            case MUSCLE_GAIN -> {
                if (muscleGroup == MuscleGroup.LEGS || muscleGroup == MuscleGroup.BACK) {
                    return new RepScheme(4, 10, 60);
                }
                return new RepScheme(3, 12, 60);
            }
            case STRENGTH -> {
                if (muscleGroup == MuscleGroup.LEGS || muscleGroup == MuscleGroup.CHEST) {
                    return new RepScheme(5, 5, 120); // תרגילי בסיס
                }
                return new RepScheme(4, 6, 90);
            }
            case STREET_WORKOUT, IMPROVE_ENDURANCE -> {
                return new RepScheme(4, 15, 45);
            }
            case STAY_FIT -> {
                return new RepScheme(3, 12, 60);
            }
            default -> {
                return new RepScheme(3, 12, 60);
            }
        }
    }
}
