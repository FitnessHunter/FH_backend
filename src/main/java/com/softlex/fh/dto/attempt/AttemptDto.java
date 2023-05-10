package com.softlex.fh.dto.attempt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.exercise.ExerciseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
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
