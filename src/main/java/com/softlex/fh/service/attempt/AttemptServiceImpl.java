package com.softlex.fh.service.attempt;

import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.attempt.AttemptRepository;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.exercise.ExerciseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AttemptServiceImpl implements AttemptService {

  private AttemptRepository attemptRepository;
  private ExerciseRepository exerciseRepository;
  private AttemptMapper attemptMapper;

  @Override
  public AttemptDto getAttempt(Long attemptId) {
    log.debug("Get attempt with id: {}", attemptId);
    Optional<Attempt> attemptOptional = attemptRepository.findById(attemptId);
    Attempt attempt = attemptOptional.orElseThrow(
            () -> new EntityNotFoundException("Wrong attempt id"));
    AttemptDto dto = attemptMapper.toDto(attempt);
    log.debug("Return attempt {}", dto);
    return dto;
  }

  @Override
  public AttemptDto createAttempt(CreateAttemptRequest createAttemptRequest) {
    log.debug("Create attempt {}", createAttemptRequest);
    Optional<Exercise> exerciseOptional = exerciseRepository.findById(
            createAttemptRequest.getExerciseId());
    Exercise exercise = exerciseOptional.orElseThrow(
            () -> new EntityNotFoundException("Wrong exercise id"));
    Attempt attempt = attemptMapper.toEntity(createAttemptRequest, exercise);
    Attempt savedAttempt = attemptRepository.save(attempt);
    AttemptDto dto = attemptMapper.toDto(savedAttempt);
    log.debug("Return created attempt {}", dto);
    return dto;
  }
}
