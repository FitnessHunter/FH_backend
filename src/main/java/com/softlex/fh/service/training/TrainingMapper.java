package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.service.program.ProgramMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProgramMapper.class})
public interface TrainingMapper {

  @Mapping(target = "id", ignore = true)
  Training toEntity(CreateTrainingRequest createTrainingRequest, Program program);

  @Mapping(target = "programDto", ignore = true)
  @Mapping(target = "exerciseDtoList", source = "exerciseList")
  TrainingDto toDto(Training training);
}
