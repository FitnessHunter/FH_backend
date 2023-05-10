package com.softlex.fh.entity.program;

import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.user.User;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Jacksonized
@Builder
@Table(name = "program")
public class Program {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "owner_id", nullable = false)
  private User owner;
  @ManyToOne
  @JoinColumn(name = "sportsman_id", nullable = false)
  private User sportsman;
  @Column(name = "program_description")
  private String programDescription;
  @Column(name = "program_name")
  private String programName;
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "program")
  private List<Training> trainingList;
}
