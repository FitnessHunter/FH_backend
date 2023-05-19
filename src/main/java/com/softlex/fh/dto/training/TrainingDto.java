package com.softlex.fh.dto.training;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.program.ProgramDto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

  private Long id;
  @NotNull
  private String name;
  @NotNull
  @JsonFormat(pattern = "dd.MM.yyyy")
  private LocalDate date;
  private String description;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private ProgramDto programDto;
  private List<ExerciseDto> exerciseDtoList;

}
