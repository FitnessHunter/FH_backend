package com.softlex.fh.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CreateAttemptRequest {

  private Long exerciseId;
  private Long weight;
  private Long duration;
  private Long repetition;
  private Long distance;
  private Long restTime;
}
