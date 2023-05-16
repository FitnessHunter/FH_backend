package com.softlex.fh.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.entity.program.Program;
import com.softlex.fh.entity.program.ProgramRepository;
import com.softlex.fh.entity.user.UserRepository;
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
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.softlex.fh.TConst.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestContainerConfig.class})
@WebAppConfiguration
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProgramIT {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeTransaction
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        userRepository.save(DEFAULT_USER);
        userRepository.save(DEFAULT_USER_SPORTSMEN);
    }

    @AfterEach
    public void tierDown() {
        userRepository.deleteAll();
        programRepository.deleteAll();
    }

    @Test
    @Transactional
    @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
    public void createProgram_ShouldSave() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(
                        post("/api/program")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(DEFAULT_CREATE_PROGRAM_REQUEST)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(mapper.writeValueAsString(DEFAULT_PROGRAM_DTO)));
        List<Program> all = programRepository.findAll();
        Assertions.assertEquals(1, all.size());
    }

    @Test
    @Transactional
    @WithUserDetails(value = DEFAULT_USER_EMAIL, userDetailsServiceBeanName = "customUserDetailsService")
    public void getProgram_ShouldReturn() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        programRepository.save(DEFAULT_PROGRAM);
        mockMvc.perform(
                        get("/api/program/" + DEFAULT_PROGRAM.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(mapper.writeValueAsString(DEFAULT_PROGRAM_DTO)));
    }
}
