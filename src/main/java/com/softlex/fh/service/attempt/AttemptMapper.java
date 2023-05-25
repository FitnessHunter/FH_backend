package com.softlex.fh.service.attempt;

import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.exercise.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
public interface AttemptMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "restTime", source = "createAttemptRequest.restTime")
  Attempt toEntity(CreateAttemptRequest createAttemptRequest, Exercise exercise);

  @Mapping(target = "exerciseDto", ignore = true)
  AttemptDto toDto(Attempt exercise);
}
