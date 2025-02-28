package com.galangel.trainingservice.workoutgenerator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ExerciseEntity {
    private String name;
    private String mainMuscle;
    private String imageUrl;
    private int sets;
    private int reps;
    private int rest;



    public String toString() {
        return name + " " + sets + "x" + reps + " " + rest + "s";
    }
}
