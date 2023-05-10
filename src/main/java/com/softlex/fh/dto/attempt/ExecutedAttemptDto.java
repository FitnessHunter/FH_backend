package com.softlex.fh.dto.attempt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutedAttemptDto extends AttemptDto {

  private AttemptDto parentAttemptDto;

}
