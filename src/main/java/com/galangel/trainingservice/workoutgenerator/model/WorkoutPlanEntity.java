package com.galangel.trainingservice.workoutgenerator.model;

import com.galangel.trainingservice.workoutgenerator.enums.Goal;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a full weekly workout plan, including goal and per-day training breakdown.
 */
@Getter @Setter
public class WorkoutPlanEntity {
    private Goal goal;
    private List<DayPlanEntity> days;

    public WorkoutPlanEntity() {
        days=new ArrayList<>();
    }

    public void addDayPlan(DayPlanEntity dayPlan) {
        if (days == null) days = new ArrayList<>();
        days.add(dayPlan);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Goal: ").append(goal).append("\n");

        if (days != null) {
            for (DayPlanEntity dayPlan : days) {
                sb.append(dayPlan.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
