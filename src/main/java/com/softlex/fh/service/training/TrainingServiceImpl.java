package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.training.TrainingRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TrainingServiceImpl implements TrainingService {

  private TrainingMapper trainingMapper;
  private TrainingRepository trainingRepository;
  private ProgramRepository programRepository;

  @Override
  public Training getTraining(Long trainingId) {
    Optional<Training> trainingOptional = trainingRepository.findById(trainingId);
    if(trainingOptional.isPresent()){
      return trainingOptional.get();
    } else {
      throw new EntityNotFoundException("Wrong training id");
    }
  }

  @Override
  public Training createTraining(CreateTrainingRequest createTrainingRequest) {
    Optional<Program> programOptional = programRepository.findById(createTrainingRequest.getProgramId());
    if(programOptional.isPresent()) {
      Program program = programOptional.get();
      Training training = trainingMapper.toEntity(createTrainingRequest, program);
      return trainingRepository.save(training);
    } else {
      throw new EntityNotFoundException("Wrong program id");
    }
  }
}
