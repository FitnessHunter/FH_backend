package com.softlex.fh;

import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.dto.request.CreateTrainingRequest;
import com.softlex.fh.dto.training.TrainingDto;
import com.softlex.fh.dto.user.UserDto;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.user.User;

import java.util.Collections;

import static com.softlex.fh.TConst.*;


public class TCreator {

  public static ExerciseDto getDefaultExerciseDto() {
    return ExerciseDto.builder()
            .id(1L)
            .trainingDto(getDefaultTrainingDto())
            .restTime(60)
            .ordinalNumber(1)
            .attemptDtoList(Collections.emptyList())
            .build();
  }

  public static CreateExerciseRequest getCreateExerciseRequest() {
    return new CreateExerciseRequest()
            .setTrainingId(DEFAULT_TRAINING_ID)
            .setOrdinalNumber(DEFAULT_ORDINAL_NUMBER)
            .setRestTime(DEFAULT_EXERCISE_REST_TIME);
  }

  public static CreateAttemptRequest getCreateAttemptRequest() {
    return new CreateAttemptRequest()
            .setExerciseId(1L)
            .setWeight(DEFAULT_WEIGHT)
            .setDuration(DEFAULT_DURATION)
            .setRepetition(DEFAULT_REPETITION)
            .setDistance(DEFAULT_DISTANCE)
            .setRestTime(TConst.DEFAULT_REST_TIME);
  }

  public static Exercise getDefaultExercise() {
    return Exercise.builder()
            .id(1L)
            .training(getDefaultTraining())
            .restTime(60)
            .ordinalNumber(1)
            .attemptList(Collections.emptyList())
            .build();
  }

  public static CreateTrainingRequest getDefaultCreateTrainingRequest() {
    return new CreateTrainingRequest()
            .setDate(DEFAULT_TRAINING_DATE)
            .setName(DEFAULT_TRAINING_NAME)
            .setDescription(DEFAULT_TRAINING_DESCRIPTION)
            .setProgramId(1L);
  }

  public static Training getDefaultTraining() {
    return Training.builder()
            .id(DEFAULT_TRAINING_ID)
            .name(DEFAULT_TRAINING_NAME)
            .date(DEFAULT_TRAINING_DATE)
            .description(DEFAULT_TRAINING_DESCRIPTION)
            .program(getDefaultProgram())
            .build();
  }

  public static User getDefaultUser() {
    return User.builder()
            .id(1L)
            .firstName("FName")
            .lastName("LName")
            .email(DEFAULT_USER_EMAIL)
            .password("password")
            .build();
  }

  public static UserDto getDefaultUserDto() {
    return UserDto.builder()
            .id(1L)
            .firstName("FName")
            .lastName("LName")
            .email(DEFAULT_USER_EMAIL)
            .password("password")
            .build();
  }

  public static User getDefaultSportsmen() {
    return User.builder()
            .id(2L)
            .firstName("FName sportsmen")
            .lastName("LName sportsmen")
            .email(DEFAULT_SPORTSMAN_EMAIL)
            .password("password")
            .build();
  }

  public static UserDto getDefaultSportsmenDto() {
    return UserDto.builder()
            .id(2L)
            .firstName("FName sportsmen")
            .lastName("LName sportsmen")
            .email("sportsmen@mail.com")
            .password("password")
            .build();
  }

  public static Program getDefaultProgram() {
    return Program.builder()
            .programName(DEFAULT_PROGRAM_NAME)
            .programDescription(DEFAULT_PROGRAM_DESCRIPTION)
            .id(DEFAULT_PROGRAM_ID)
            .owner(getDefaultUser())
            .sportsman(getDefaultSportsmen())
            .build();
  }

  public static ProgramDto getDefaultProgramDto() {
    return ProgramDto.builder()
            .programName("Program name")
            .programDescription("Program description")
            .id(1L)
            .owner(getDefaultUserDto())
            .sportsman(getDefaultSportsmenDto())
            .trainingDtoList(Collections.emptyList())
            .build();
  }

  public static CreateProgramRequest getCreateProgramRequest() {
    return new CreateProgramRequest()
            .setOwnerId(1L)
            .setProgramName("Program name")
            .setProgramDescription("Program description")
            .setSportsmanId(2L);
  }

  public static TrainingDto getDefaultTrainingDto() {
    return TrainingDto.builder()
            .id(1L)
            .name(DEFAULT_TRAINING_NAME)
            .date(DEFAULT_TRAINING_DATE)
            .description(DEFAULT_TRAINING_DESCRIPTION)
            .exerciseDtoList(Collections.emptyList())
            .programDto(getDefaultProgramDto())
            .build();
  }

  public static Attempt getDefaultAttempt() {
    return Attempt.builder()
            .id(1L)
            .exercise(getDefaultExercise())
            .restTime(DEFAULT_REST_TIME)
            .distance(DEFAULT_DISTANCE)
            .duration(DEFAULT_DURATION)
            .repetition(DEFAULT_REPETITION)
            .weight(DEFAULT_WEIGHT)
            .build();
  }

  public static AttemptDto getDefaultAttemptDto() {
    return AttemptDto.builder()
            .id(1L)
            .exerciseDto(getDefaultExerciseDto())
            .restTime(60L)
            .distance(10L)
            .duration(120L)
            .repetition(5L)
            .weight(100L)
            .build();
  }

}
