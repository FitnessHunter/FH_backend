package com.softlex.fh.service.exercise;

import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.exercise.ExerciseRepository;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.training.TrainingRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

  private ExerciseRepository exerciseRepository;
  private TrainingRepository trainingRepository;
  private ExerciseMapper exerciseMapper;

  @Override
  public ExerciseDto getExercise(Long exerciseId) {
    Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
    Exercise exercise = exerciseOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong exercise id"));
    return exerciseMapper.toDto(exercise);
  }

  @Override
  public ExerciseDto createExercise(CreateExerciseRequest createExerciseRequest) {
    Optional<Training> trainingOptional = trainingRepository.findById(
        createExerciseRequest.getTrainingId());
    Training training = trainingOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong training id"));
    Exercise exercise = exerciseMapper.toEntity(createExerciseRequest, training);
    Exercise savedExercise = exerciseRepository.save(exercise);
    return exerciseMapper.toDto(savedExercise);
  }
}
