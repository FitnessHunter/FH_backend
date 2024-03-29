package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.exercise.ExerciseRepository;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.training.TrainingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static com.softlex.fh.TConst.*;
import static com.softlex.fh.TCreator.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestContainerConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ExerciseIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private TrainingRepository trainingRepository;

  @Autowired
  private ProgramRepository programRepository;

  private Training training;

  @BeforeEach
  public void setup() {
    Training defaultTraining = getDefaultTraining();
    Program defaultProgram = getDefaultProgram();
    Program program = programRepository.save(defaultProgram);
    defaultTraining.setProgram(program);
    training = trainingRepository.save(defaultTraining);
  }

  @AfterEach
  public void teardown() {
    exerciseRepository.deleteAll();
    trainingRepository.deleteAll();
    programRepository.deleteAll();
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void createExercise_ShouldSave() throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    CreateExerciseRequest createExerciseRequest = getCreateExerciseRequest();
    createExerciseRequest.setTrainingId(training.getId());
    mockMvc.perform(
                    put("/api/exercise")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(createExerciseRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.ordinalNumber").value(DEFAULT_ORDINAL_NUMBER))
            .andExpect(jsonPath("$.restTime").value(DEFAULT_EXERCISE_REST_TIME));
    List<Exercise> all = exerciseRepository.findAll();
    Assertions.assertEquals(1, all.size());
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void getExercise_ShouldReturn() throws Exception {

    Exercise defaultExercise = getDefaultExercise();
    defaultExercise.setAttemptList(Collections.emptyList());
    defaultExercise.setTraining(training);
    Exercise save = exerciseRepository.save(defaultExercise);
    mockMvc.perform(
                    get("/api/exercise/" + save.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.ordinalNumber").value(DEFAULT_ORDINAL_NUMBER))
            .andExpect(jsonPath("$.restTime").value(DEFAULT_EXERCISE_REST_TIME));
  }
}
