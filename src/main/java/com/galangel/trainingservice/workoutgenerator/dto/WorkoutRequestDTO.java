package com.galangel.trainingservice.workoutgenerator.dto;

import com.galangel.trainingservice.workoutgenerator.enums.Goal;
import com.galangel.trainingservice.workoutgenerator.enums.WorkoutSplitType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutRequestDTO {
    @Min(12) // Minimum age 12
    @Max(60) // Maximum age 60
    private int age;

    @Min(0) // Minimum years of experience 0
    private double yearsOfExperience;

    @Min(1) // Minimum days per week 1
    @Max(7) // Maximum days per week 7
    private int daysPerWeek;

    @NotNull
    private Goal goal;

    @NotNull
    private WorkoutSplitType splitType;

    public WorkoutRequestDTO(int age, double yearsOfExperience, int daysPerWeek, Goal goal, WorkoutSplitType splitType) {
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
        this.daysPerWeek = daysPerWeek;
        this.goal = goal;
        this.splitType = splitType;
    }

    @Override
    public String toString() {
        return "WorkoutRequestDTO{" +
                "age=" + age +
                ", yearsOfExperience=" + yearsOfExperience +
                ", daysPerWeek=" + daysPerWeek +
                ", goal=" + goal +
                ", splitType=" + splitType +
                '}';
    }

}
