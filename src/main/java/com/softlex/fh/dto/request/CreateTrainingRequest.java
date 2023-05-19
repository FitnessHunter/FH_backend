package com.softlex.fh.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingRequest {

  private Long programId;
  private String name;
  private String description;
  @JsonFormat(pattern = "dd.MM.yyyy")
  private LocalDate date;
}
