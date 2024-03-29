package com.softlex.fh.unit;

import com.softlex.fh.ApiApplication;
import com.softlex.fh.config.TestContainerConfig;
import com.softlex.fh.entity.program.ProgramRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {TestContainerConfig.class, ApiApplication.class})
@Disabled
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
    }
}
