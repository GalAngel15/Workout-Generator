package com.galangel.trainingservice.workoutgenerator.service;

import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.DayPlanEntity;
import com.galangel.trainingservice.workoutgenerator.repository.WorkoutPlanRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkoutPlanServiceImpl implements WorkoutPlanService{
    private final WorkoutPlanRepository workoutPlanRepository;

    public WorkoutPlanServiceImpl(WorkoutPlanRepository workoutPlanRepository) {
        this.workoutPlanRepository = workoutPlanRepository;
    }

    @Override
    public DayPlanEntity generateWorkoutPlan(WorkoutRequestDTO request) {
        // לוגיקה בסיסית ליצירת תוכנית אימונים
        DayPlanEntity workoutPlan = new DayPlanEntity();
        workoutPlan.setUserId(request.getUserId());
        workoutPlan.setWorkoutDays(4); // ברירת מחדל - 4 ימים בשבוע

        // שמירה ב-Firebase
        workoutPlanRepository.saveWorkoutPlan(workoutPlan);

        return workoutPlan;
    }
}
