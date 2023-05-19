package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.exercise.ExerciseDto;
import com.softlex.fh.dto.request.CreateExerciseRequest;
import com.softlex.fh.entity.exercise.Exercise;
import com.softlex.fh.entity.exercise.ExerciseRepository;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.training.Training;
import com.softlex.fh.entity.training.TrainingRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
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

import static com.softlex.fh.TConst.DEFAULT_USER_EMAIL;
import static com.softlex.fh.TCreator.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestContainerConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExerciseIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ExerciseRepository exerciseRepository;

  @Autowired
  private TrainingRepository trainingRepository;

  @Autowired
  private ProgramRepository programRepository;

  @AfterAll
  public void teardown() {
    exerciseRepository.deleteAll();
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void createExercise_ShouldSave() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ExerciseDto defaultExerciseDto = getDefaultExerciseDto();
    defaultExerciseDto.setAttemptDtoList(Collections.emptyList());
    Training defaultTraining = getDefaultTraining();
    Program defaultProgram = getDefaultProgram();
    Program program = programRepository.save(defaultProgram);
    defaultTraining.setProgram(program);
    Long trainingId = trainingRepository.save(defaultTraining).getId();
    CreateExerciseRequest createExerciseRequest = getCreateExerciseRequest();
    createExerciseRequest.setTrainingId(trainingId);
    mockMvc.perform(
                    post("/api/exercise")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(createExerciseRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(mapper.writeValueAsString(defaultExerciseDto)));
    List<Exercise> all = exerciseRepository.findAll();
    Assertions.assertEquals(1, all.size());
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void getProgram_ShouldReturn() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    ExerciseDto defaultExerciseDto = getDefaultExerciseDto();
    defaultExerciseDto.setAttemptDtoList(Collections.emptyList());
    mockMvc.perform(
                    get("/api/exercise/" + defaultExerciseDto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(mapper.writeValueAsString(defaultExerciseDto)));
  }
}
