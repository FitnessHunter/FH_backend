package com.softlex.fh.dto.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.program.ProgramDto;
import java.time.LocalDate;
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
public class TrainingDto {

  @NotNull
  LocalDate date;
  private Long id;
  @NotNull
  private String name;
  private String description;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private ProgramDto programDto;
  private List<ExerciseDto> exerciseDtoList;

}
