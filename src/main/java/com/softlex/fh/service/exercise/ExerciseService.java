package com.softlex.fh.service.exercise;

import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;

public interface ExerciseService {

    Exercise getExercise(Long exerciseId);

    Exercise createExercise(CreateExerciseRequest createExerciseRequest);
}
