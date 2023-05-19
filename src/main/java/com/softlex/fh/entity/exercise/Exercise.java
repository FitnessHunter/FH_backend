package com.softlex.fh.entity.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.training.Training;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "exercise")
public class Exercise {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  @JoinColumn(name = "training_id", nullable = false)
  private Training training;
  @Column(name = "ordinal_number")
  @NotNull
  private Integer ordinalNumber;
  @Column(name = "rest_time")
  private Integer restTime;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "exercise")
  private List<Attempt> attemptList;
}
