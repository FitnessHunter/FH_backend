package com.softlex.fh.dto.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.entity.program.Program;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDto {

  private Long id;
  @NotNull
  private String name;
  private String description;
  @NotNull
  LocalDate date;
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  private ProgramDto program;

}
