package com.softlex.fh.controller.excersice;

import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.service.exercise.ExerciseService;
import com.softlex.fh.web.BaseControllerAdvice;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ExerciseController extends BaseControllerAdvice {

  private ExerciseService exerciseService;

  @GetMapping("/exercise/{exerciseId}")
  public ExerciseDto getProgram(@PathVariable Long exerciseId) {
    return exerciseService.getExercise(exerciseId);
  }

  @PutMapping("/exercise")
  @ResponseStatus(HttpStatus.CREATED)
  public ExerciseDto createProgram(@RequestBody CreateExerciseRequest createExerciseRequest) {
    return exerciseService.createExercise(createExerciseRequest);
  }
}
