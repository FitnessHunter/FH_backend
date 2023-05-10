package com.softlex.fh.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingRequest {

  private Long programId;
  private String name;
  private String description;
  @JsonFormat(pattern = "dd.MM.yyyy")
  private LocalDate date;
}
