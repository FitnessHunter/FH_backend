package com.softlex.fh.controller.excersice;

import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.service.exercise.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ExerciseController {

  private ExerciseService exerciseService;

  @GetMapping("/exercise/{exerciseId}")
  public ExerciseDto getProgram(@PathVariable Long exerciseId) {
    return exerciseService.getExercise(exerciseId);
  }

  @PostMapping("/exercise")
  public ExerciseDto createProgram(CreateExerciseRequest createExerciseRequest) {
    return exerciseService.createExercise(createExerciseRequest);
  }
}
