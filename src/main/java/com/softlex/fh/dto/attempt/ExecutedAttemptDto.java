package com.softlex.fh.dto.attempt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutedAttemptDto extends AttemptDto {

  private AttemptDto parentAttemptDto;

}
