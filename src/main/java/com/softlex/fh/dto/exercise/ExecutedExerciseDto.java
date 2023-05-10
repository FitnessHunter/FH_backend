package com.softlex.fh.dto.exercise;

import com.softlex.fh.entity.exercise.ExerciseCondition;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutedExerciseDto extends ExerciseDto {

  private ExerciseCondition condition;
  private String comment;
  private LocalDate startDateTime;
  private LocalDate endDateTime;

}
