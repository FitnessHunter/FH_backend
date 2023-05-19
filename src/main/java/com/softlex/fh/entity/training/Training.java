package com.softlex.fh.entity.training;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.program.Program;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "training")
@EqualsAndHashCode
public class Training {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  @NotNull
  private LocalDate date;

  @Column(name = "name")
  @NotNull
  private String name;

  @Column(name = "description")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @JsonIgnore
  @JoinColumn(name = "program_id", nullable = false)
  private Program program;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "training")
  private List<Exercise> exerciseList;

}
