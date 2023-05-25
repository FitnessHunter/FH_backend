package com.softlex.fh.controller.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.service.training.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainingController {

  private TrainingService trainingService;

  @GetMapping("/training/{trainingId}")
  public TrainingDto getProgram(@PathVariable Long trainingId) {
    return trainingService.getTraining(trainingId);
  }

  @PutMapping("/training")
  @ResponseStatus(HttpStatus.CREATED)
  public TrainingDto createProgram(@RequestBody CreateTrainingRequest createTrainingRequest) {
    return trainingService.createTraining(createTrainingRequest);
  }
}
