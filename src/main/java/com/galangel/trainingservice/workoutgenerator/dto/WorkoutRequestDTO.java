package com.galangel.trainingservice.workoutgenerator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutRequestDTO {
    private int age;
    private int yearsExperience;
    private Goal goal;

    public WorkoutRequestDTO() {
    }

    public WorkoutRequestDTO(int age, int yearsExperience, Goal goal) {
        this.age = age;
        this.yearsExperience = yearsExperience;
        this.goal = goal;
    }

    public String toString() {
        return "Age: " + age + " Experience: " + yearsExperience + " Goal: " + goal;
    }
}
