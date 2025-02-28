package com.galangel.trainingservice.workoutgenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ExerciseDTO {
    private String name;
    private String mainMuscle;
    private String imageUrl;
}

