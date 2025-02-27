package com.galangel.trainingservice.workoutgenerator.controller;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.entity.WorkoutPlanEntity;
import com.galangel.trainingservice.workoutgenerator.service.WorkoutPlanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkoutPlanController {
    private final WorkoutPlanService workoutPlanService;

    public WorkoutPlanController(WorkoutPlanService workoutPlanService) {
        this.workoutPlanService = workoutPlanService;
    }

    @PostMapping("/generate")
    public WorkoutPlanEntity generateWorkoutPlan(@RequestBody WorkoutRequestDTO request){
        return workoutPlanService.generateWorkoutPlan(request);
    }

}
