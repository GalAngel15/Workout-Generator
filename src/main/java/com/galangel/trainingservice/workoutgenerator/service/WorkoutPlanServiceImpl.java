package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.entity.WorkoutPlanEntity;
import com.galangel.trainingservice.workoutgenerator.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService{
    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public WorkoutPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        // לוגיקה בסיסית ליצירת תוכנית אימונים
        WorkoutPlanEntity workoutPlan = new WorkoutPlanEntity();
        workoutPlan.setUserId(request.getUserId());
        workoutPlan.setWorkoutDays(4); // ברירת מחדל - 4 ימים בשבוע

        // שמירה ב-Firebase
        workoutPlanRepository.saveWorkoutPlan(workoutPlan);

        return workoutPlan;
    }
}
