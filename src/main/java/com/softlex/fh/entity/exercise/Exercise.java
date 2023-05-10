package com.softlex.fh.entity.exercise;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.training.Training;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
