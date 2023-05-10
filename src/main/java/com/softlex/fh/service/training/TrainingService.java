package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.entity.training.Training;

public interface TrainingService {

  TrainingDto getTraining(Long trainingId);

  TrainingDto createTraining(CreateTrainingRequest createTrainingRequest);

}
