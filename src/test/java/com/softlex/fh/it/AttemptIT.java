package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.attempt.AttemptDto;
import com.softlex.fh.dto.request.CreateAttemptRequest;
import com.softlex.fh.entity.attempt.Attempt;
import com.softlex.fh.entity.attempt.AttemptRepository;
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
public class AttemptIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AttemptRepository attemptRepository;

  @Autowired
  private TrainingRepository trainingRepository;

  @Autowired
  private ProgramRepository programRepository;

  @Autowired
  private ExerciseRepository exerciseRepository;

  private Exercise exercise;

  @BeforeEach
  public void setup() {
    Program defaultProgram = getDefaultProgram();
    Program program = programRepository.save(defaultProgram);
    Training defaultTraining = getDefaultTraining();
    defaultTraining.setProgram(program);
    Training training = trainingRepository.save(defaultTraining);
    Exercise defaultExercise = getDefaultExercise();
    defaultExercise.setTraining(training);
    exercise = exerciseRepository.save(defaultExercise);
  }

  @AfterEach
  public void teardown() {
    attemptRepository.deleteAll();
    exerciseRepository.deleteAll();
    trainingRepository.deleteAll();
    programRepository.deleteAll();
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void createAttempt_ShouldSave() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    CreateAttemptRequest createAttemptRequest = getCreateAttemptRequest();
    createAttemptRequest.setExerciseId(exercise.getId());
    AttemptDto defaultAttemptDto = getDefaultAttemptDto();
    mockMvc.perform(
                    put("/api/attempt")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(createAttemptRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(mapper.writeValueAsString(defaultAttemptDto)));
    List<Attempt> all = attemptRepository.findAll();
    Assertions.assertEquals(1, all.size());
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void getAttempt_ShouldReturn() throws Exception {
    Attempt defaultAttempt = getDefaultAttempt();
    defaultAttempt.setExercise(exercise);
    Attempt attempt = attemptRepository.save(defaultAttempt);
    mockMvc.perform(
                    get("/api/attempt/" + attempt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT))
            .andExpect(jsonPath("$.duration").value(DEFAULT_DURATION))
            .andExpect(jsonPath("$.repetition").value(DEFAULT_REPETITION))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE))
            .andExpect(jsonPath("$.restTime").value(DEFAULT_REST_TIME));
  }
}
