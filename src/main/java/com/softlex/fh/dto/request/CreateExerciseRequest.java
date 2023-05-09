package com.softlex.fh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {

  private Long trainingId;
  private String ordinalNumber;
  private Integer restTime;
}
