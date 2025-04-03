package com.galangel.trainingservice.workoutgenerator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutRequestDTO {
    private int age;
    private double yearsOfExperience;
    private int daysPerWeek;
    private Goal goal;

    public WorkoutRequestDTO() {
    }
    public WorkoutRequestDTO(int age, double yearsOfExperience, int daysPerWeek, Goal goal) {
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
        this.daysPerWeek = daysPerWeek;
        this.goal = goal;
    }

    public String toString() {
        return "WorkoutRequestDTO{" +
                "age=" + age +
                ", yearsOfExperience=" + yearsOfExperience +
                ", daysPerWeek=" + daysPerWeek +
                ", goal=" + goal +
                '}';
    }
}
