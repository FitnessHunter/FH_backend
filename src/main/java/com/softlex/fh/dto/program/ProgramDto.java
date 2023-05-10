package com.softlex.fh.dto.program;

import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.dto.user.UserDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramDto {

  private Long id;
  private UserDto owner;
  private UserDto sportsman;
  private String programDescription;
  private String programName;
  private List<TrainingDto> trainingDtoList;
}
