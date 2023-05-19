package com.softlex.fh.entity.attempt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.entity.exercise.Exercise;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "attempt")
@Inheritance(strategy = InheritanceType.JOINED)
public class Attempt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
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
