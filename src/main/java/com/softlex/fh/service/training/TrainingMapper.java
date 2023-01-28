package com.softlex.fh.service.training;

import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.training.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TrainingMapper {

  @Mapping(target = "id", ignore = true)
  Training toEntity(CreateTrainingRequest createTrainingRequest, Program program);

}
