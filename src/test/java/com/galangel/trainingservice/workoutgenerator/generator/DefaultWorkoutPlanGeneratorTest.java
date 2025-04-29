package com.galangel.trainingservice.workoutgenerator.generator;

import com.galangel.trainingservice.workoutgenerator.components.Converter;
import com.galangel.trainingservice.workoutgenerator.dto.ExerciseDTO;
import com.galangel.trainingservice.workoutgenerator.dto.Goal;
import com.galangel.trainingservice.workoutgenerator.dto.WorkoutRequestDTO;
import com.galangel.trainingservice.workoutgenerator.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultWorkoutPlanGeneratorTest {

    private DefaultWorkoutPlanGenerator generator;
    private WorkoutTemplateGenerator templateGenerator;
    private ExerciseSelectionStrategy exerciseSelectionStrategy;
    private Converter converter;
    private ExercisePicker exercisePicker;
    private RepSchemeSelector repSchemeSelector;

    @BeforeEach
    void setup() {
        templateGenerator = mock(WorkoutTemplateGenerator.class);
        exerciseSelectionStrategy = mock(ExerciseSelectionStrategy.class);
        converter = mock(Converter.class);
        exercisePicker = mock(ExercisePicker.class);
        repSchemeSelector = mock(RepSchemeSelector.class);

        generator = new DefaultWorkoutPlanGenerator(templateGenerator, exerciseSelectionStrategy, converter, exercisePicker, repSchemeSelector);
    }

    @Test
    void generateWorkoutPlan_success() {
        // Arrange
        WorkoutRequestDTO request = new WorkoutRequestDTO(25, 2.5, 3, Goal.MUSCLE_GAIN);
        ExerciseDTO exerciseDTO = new ExerciseDTO("Push Up", "CHEST", "url");

        Map<Integer, List<MuscleGroup>> template = Map.of(
                0, List.of(MuscleGroup.CHEST),
                2, List.of(MuscleGroup.BACK)
        );

        when(templateGenerator.getTemplate(any(), eq(3))).thenReturn(template);
        when(exerciseSelectionStrategy.getRecommendedCount(any(), any())).thenReturn(1);
        when(exercisePicker.pickExercises(anyList(), any(), anyInt())).thenReturn(List.of(exerciseDTO));
        when(repSchemeSelector.getSchemeFor(any(), any())).thenReturn(new RepScheme(3, 12, 60));
        when(converter.convertToEntity(any(), anyInt(), anyInt(), anyInt()))
                .thenReturn(new ExerciseEntity("Push Up", "CHEST", "url", 3, 12, 60));

        // Act
        WorkoutPlanEntity plan = generator.generate(request, List.of(exerciseDTO));

        // Assert
        assertNotNull(plan);
        assertEquals(Goal.MUSCLE_GAIN, plan.getGoal());
        assertEquals(3, plan.getDays().size());
    }
}
