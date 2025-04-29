package com.galangel.trainingservice.workoutgenerator.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
public class DayPlanEntity {
    private int dayNumber;
    private List<ExerciseEntity> exercises;

    public DayPlanEntity(int dayNumber) {
        this.dayNumber = dayNumber;
        this.exercises = new ArrayList<>();
    }

    public DayPlanEntity(int dayNumber, List<ExerciseEntity> exercises) {
        this.dayNumber = dayNumber;
        this.exercises = exercises != null ? exercises : new ArrayList<>();
    }

    public void addExercise(ExerciseEntity exercise) {
        if (exercises == null) exercises = new ArrayList<>();
        exercises.add(exercise);
    }

    public void removeExercise(ExerciseEntity exercise) {
        if (exercises != null) {
            exercises.remove(exercise);
        }
    }

    public String toString() {
        return String.join("\n", exercises.stream().map(ExerciseEntity::toString).toList());
    }

}
