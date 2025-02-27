package com.galangel.trainingservice.workoutgenerator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DayPlanEntity {
    List<ExerciseEntity> exercises;

    public DayPlanEntity() {
        exercises=new ArrayList<>();
    }

    public DayPlanEntity(List<ExerciseEntity> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(ExerciseEntity exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(ExerciseEntity exercise) {
        exercises.remove(exercise);
    }

//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        for (ExerciseEntity exercise : exercises) {
//            sb.append(exercise.toString());
//            sb.append("\n");
//        }
//        return sb.toString();
//    }

    public String toString() {
        return String.join("\n", exercises.stream().map(ExerciseEntity::toString).toList());
    }

}
