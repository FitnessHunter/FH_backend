package com.softlex.fh.service.training;

import static org.junit.jupiter.api.Assertions.*;

import com.softlex.fh.ApiApplication;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.container.SingletonPostgresqlContainer;
import com.softlex.fh.entity.program.ProgramRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {TestContainerConfig.class, ApiApplication.class})
class TrainingDtoServiceImplTest {
  @Autowired
  private ProgramRepository programRepository;

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void getTraining() {
    System.out.println("123");
  }
}
