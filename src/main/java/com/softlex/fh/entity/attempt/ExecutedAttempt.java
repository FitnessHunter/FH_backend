package com.softlex.fh.entity.attempt;

import com.softlex.fh.entity.exercise.Exercise;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "executed_attempt")
public class ExecutedAttempt extends Attempt {

  @ManyToOne
  @JoinColumn(name = "parent_attempt_id", nullable = false)
  private Attempt parentAttempt;

}
