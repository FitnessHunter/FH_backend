package com.softlex.fh.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.training.TrainingDto;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDto {

  private Long id;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private TrainingDto trainingDto;
  @NotNull
  private Integer ordinalNumber;
  private Integer restTime;
  private List<AttemptDto> attemptDtoList;
}
