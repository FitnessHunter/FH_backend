package com.softlex.fh.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingRequest {
  private Long programId;
  private String name;
  private String description;
  @DateTimeFormat(pattern = "dd.MM.yyyy")
  private LocalDate date;
}
