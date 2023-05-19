package com.softlex.fh.dto.attempt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.exercise.ExerciseDto;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttemptDto {

  private Long id;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private ExerciseDto exerciseDto;
  private Long weight;
  private Long duration;
  private Long repetition;
  private Long distance;
  private Long restTime;

}
