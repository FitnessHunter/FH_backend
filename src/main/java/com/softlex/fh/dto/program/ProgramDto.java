package com.softlex.fh.dto.program;

import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.dto.user.UserDto;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {

  private Long id;
  private UserDto owner;
  private UserDto sportsman;
  private String programDescription;
  private String programName;
  private List<TrainingDto> trainingList;
}
