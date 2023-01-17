package com.softlex.fh.entity.training;

import com.softlex.fh.entity.program.Program;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training")
public class Training {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "name")
  @NotNull
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "date")
  @NotNull
  LocalDate date;
  @ManyToOne
  @JoinColumn(name = "program_id", nullable = false)
  private Program program;

}
