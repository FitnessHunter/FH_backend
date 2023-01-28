package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.entity.training.Training;

public interface TrainingService {

  Training getTraining(Long trainingId);
  Training createTraining(CreateTrainingRequest createTrainingRequest);

}
