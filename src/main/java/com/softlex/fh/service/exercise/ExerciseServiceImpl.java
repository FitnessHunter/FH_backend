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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ExerciseServiceImpl implements ExerciseService {

  private ExerciseRepository exerciseRepository;
  private TrainingRepository trainingRepository;
  private ExerciseMapper exerciseMapper;

  @Override
  public ExerciseDto getExercise(Long exerciseId) {
    log.debug("Get exercise with id: {}", exerciseId);
    Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
    Exercise exercise = exerciseOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong exercise id"));
    ExerciseDto dto = exerciseMapper.toDto(exercise);
    log.debug("Return exercise {}", dto);
    return dto;
  }

  @Override
  public ExerciseDto createExercise(CreateExerciseRequest createExerciseRequest) {
    log.debug("Create exercise {}", createExerciseRequest);
    Optional<Training> trainingOptional = trainingRepository.findById(
        createExerciseRequest.getTrainingId());
    Training training = trainingOptional.orElseThrow(
        () -> new EntityNotFoundException("Wrong training id"));
    Exercise exercise = exerciseMapper.toEntity(createExerciseRequest, training);
    Exercise savedExercise = exerciseRepository.save(exercise);
    ExerciseDto dto = exerciseMapper.toDto(savedExercise);
    log.debug("Return created exercise {}", dto);
    return dto;
  }
}
