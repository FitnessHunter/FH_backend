package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.request.CreateTrainingRequest;
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

import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.softlex.fh.TConst.*;
import static com.softlex.fh.TCreator.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestContainerConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TrainingIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProgramRepository programRepository;

  @Autowired
  private TrainingRepository trainingRepository;

  @AfterAll
  public void teardown() {
    trainingRepository.deleteAll();
    programRepository.deleteAll();
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void createTraining_ShouldSave() throws Exception {
    ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    Program save = programRepository.save(getDefaultProgram());
    CreateTrainingRequest defaultCreateTrainingRequest = getDefaultCreateTrainingRequest();
    defaultCreateTrainingRequest.setProgramId(save.getId());
    mockMvc.perform(
                    post("/api/training")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(defaultCreateTrainingRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.description").value(DEFAULT_TRAINING_DESCRIPTION))
            .andExpect(jsonPath("$.name").value(DEFAULT_TRAINING_NAME))
            .andExpect(jsonPath("$.date").value(DEFAULT_TRAINING_DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
    List<Training> all = trainingRepository.findAll();
    Assertions.assertEquals(1, all.size());
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void getTraining_ShouldReturn() throws Exception {
    programRepository.save(getDefaultProgram());
    trainingRepository.save(getDefaultTraining());
    mockMvc.perform(
                    get("/api/training/" + DEFAULT_TRAINING_ID))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.description").value(DEFAULT_TRAINING_DESCRIPTION))
            .andExpect(jsonPath("$.name").value(DEFAULT_TRAINING_NAME))
            .andExpect(jsonPath("$.date").value(DEFAULT_TRAINING_DATE.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
  }
}
