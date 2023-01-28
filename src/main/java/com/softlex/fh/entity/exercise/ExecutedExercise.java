package com.softlex.fh.entity.exercise;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executed_exercise")
public class ExecutedExercise extends Exercise {

  @Enumerated(EnumType.STRING)
  @Column(name = "condition")
  private ExerciseCondition condition;
  @Column(name = "comment")
  private String comment;
  @Column(name = "start_date_time")
  private LocalDate startDateTime;
  @Column(name = "end_date_time")
  private LocalDate endDateTime;
  @Column(name = "rest_time")
  private Long restTime;

}
