package com.softlex.fh.controller.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.service.training.TrainingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class TrainingController {

  private TrainingService trainingService;

  @GetMapping("/training/{trainingId}")
  public TrainingDto getProgram(@PathVariable Long trainingId) {
    return trainingService.getTraining(trainingId);
  }

  @PostMapping("/training")
  public TrainingDto createProgram(CreateTrainingRequest createTrainingRequest) {
    return trainingService.createTraining(createTrainingRequest);
  }
}
