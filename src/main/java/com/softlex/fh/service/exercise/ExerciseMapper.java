package com.softlex.fh.service.exercise;

import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.training.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restTime", source = "createExerciseRequest.restTime")
    @Mapping(target = "ordinalNumber", source = "createExerciseRequest.ordinalNumber")
    Exercise toEntity(CreateExerciseRequest createExerciseRequest, Training training);
}
