package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
public class ProgramIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProgramRepository programRepository;

  @AfterEach
  public void teardown() {
    programRepository.deleteAll();
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void createProgram_ShouldSave() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    CreateProgramRequest createProgramRequest = getCreateProgramRequest();
    ProgramDto defaultProgramDto = getDefaultProgramDto();
    mockMvc.perform(
                    put("/api/program")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsString(createProgramRequest)))
            .andExpect(status().isCreated())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(mapper.writeValueAsString(defaultProgramDto)));
    List<Program> all = programRepository.findAll();
    Assertions.assertEquals(1, all.size());
  }

  @Test
  @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
  public void getProgram_ShouldReturn() throws Exception {
    Program defaultProgram = getDefaultProgram();
    Program program = programRepository.save(defaultProgram);
    mockMvc.perform(
                    get("/api/program/" + program.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$.owner.email").value(DEFAULT_USER_EMAIL))
            .andExpect(jsonPath("$.sportsman.email").value(DEFAULT_SPORTSMAN_EMAIL))
            .andExpect(jsonPath("$.programDescription").value(DEFAULT_PROGRAM_DESCRIPTION))
            .andExpect(jsonPath("$.programName").value(DEFAULT_PROGRAM_NAME));
  }
}
