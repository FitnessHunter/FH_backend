package com.softlex.fh.dto.exercise;

import com.softlex.fh.entity.training.Training;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {

  private Long id;
  private Training training;
  @NotNull
  private Integer ordinalNumber;
  private Integer restTime;
}
