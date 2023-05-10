package com.softlex.fh.service.program;

import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.user.User;
import com.softlex.fh.service.training.TrainingMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TrainingMapper.class})
public interface ProgramMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "programDescription", source = "createProgramRequest.programDescription")
  @Mapping(target = "programName", source = "createProgramRequest.programName")
  Program toEntity(CreateProgramRequest createProgramRequest, User owner, User sportsman);

  @Mapping(target = "trainingDtoList", source = "trainingList")
  ProgramDto toDto(Program program);
}
