package com.softlex.fh.service.exercise;

import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;

public interface ExerciseService {

    ExerciseDto getExercise(Long exerciseId);

    ExerciseDto createExercise(CreateExerciseRequest createExerciseRequest);
}
