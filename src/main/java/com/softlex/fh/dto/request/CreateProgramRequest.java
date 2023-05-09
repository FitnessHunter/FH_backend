package com.softlex.fh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateProgramRequest {

  private Long ownerId;
  private Long sportsmanId;
  private String programDescription;
  private String programName;
}
