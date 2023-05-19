package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.dto.program.ProgramDto;
import com.softlex.fh.dto.request.CreateProgramRequest;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
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

import java.util.List;

import static com.softlex.fh.TConst.DEFAULT_PROGRAM_ID;
import static com.softlex.fh.TConst.DEFAULT_USER_EMAIL;
import static com.softlex.fh.TCreator.getCreateProgramRequest;
import static com.softlex.fh.TCreator.getDefaultProgramDto;
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
public class ProgramIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ProgramRepository programRepository;

  @AfterAll
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
                    post("/api/program")
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
    ObjectMapper mapper = new ObjectMapper();
    mockMvc.perform(
                    get("/api/program/" + DEFAULT_PROGRAM_ID))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().json(mapper.writeValueAsString(getDefaultProgramDto())));
  }
}
