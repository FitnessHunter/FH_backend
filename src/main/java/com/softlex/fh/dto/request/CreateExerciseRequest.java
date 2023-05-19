package com.softlex.fh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateExerciseRequest {

  private Long trainingId;
  private Integer ordinalNumber;
  private Integer restTime;
}
