package com.galangel.trainingservice.workoutgenerator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExerciseEntity {
    String name;
    private String muscleGroup;
    int sets;
    int reps;
    int rest;

    public ExerciseEntity(String name, int sets, int reps, int rest) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }

    public String toString() {
        return name + " " + sets + "x" + reps + " " + rest + "s";
    }
}
