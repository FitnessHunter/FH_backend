package com.softlex.fh.dto.exercise;

import com.softlex.fh.entity.exercise.ExerciseCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutedExerciseDto extends ExerciseDto {

  @Enumerated(EnumType.STRING)
  @Column(name = "condition")
  private ExerciseCondition condition;
  @Column(name = "comment")
  private String comment;
  @Column(name = "start_date_time")
  private LocalDate startDateTime;
  @Column(name = "end_date_time")
  private LocalDate endDateTime;

}
