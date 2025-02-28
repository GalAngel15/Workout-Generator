package com.galangel.trainingservice.workoutgenerator.controller;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.DayPlanEntity;
import com.galangel.trainingservice.workoutgenerator.model.WorkoutPlanEntity;
import com.galangel.trainingservice.workoutgenerator.service.WorkoutPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout")
@Tag(name = "Workout Plan", description = "ניהול תוכניות אימון")
public class WorkoutPlanController {
    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @PostMapping("/generate")
    @Operation(summary = "יוצר תוכנית אימונים", description = "מחזיר תוכנית אימונים בהתאם לפרטי המתאמן")
    public WorkoutPlanEntity generateWorkoutPlan(@RequestBody WorkoutRequestDTO request){
        return workoutPlanService.generateWorkoutPlan(request);
    }

}
