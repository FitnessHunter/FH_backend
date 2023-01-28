package com.softlex.fh.entity.attempt;

import com.softlex.fh.entity.exercise.Exercise;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attempt")
@Inheritance(strategy = InheritanceType.JOINED)
public class Attempt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "exercise_id", nullable = false)
  private Exercise exercise;

  @Column(name = "weight")
  private Long weight;
  @Column(name = "duration")
  private Long duration;
  @Column(name = "repetition")
  private Long repetition;
  @Column(name = "distance")
  private Long distance;
  @Column(name = "rest_time")
  private Long restTime;

}
