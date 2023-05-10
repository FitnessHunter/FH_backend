package com.softlex.fh.dto.attempt;

import com.softlex.fh.dto.exercise.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttemptDto {

  private Long id;

  private ExerciseDto exercise;
  private Long weight;
  private Long duration;
  private Long repetition;
  private Long distance;
  private Long restTime;

}
