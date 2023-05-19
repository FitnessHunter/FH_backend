package com.softlex.fh.dto.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.training.TrainingDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
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
