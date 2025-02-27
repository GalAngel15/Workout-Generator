package com.galangel.trainingservice.workoutgenerator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutRequestDTO {
    private int age;
    private int yearsExperience;
    private Goal goal;
}
