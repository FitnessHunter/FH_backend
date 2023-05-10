package com.softlex.fh.config;

import com.softlex.fh.container.SingletonPostgresqlContainer;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
public class TestContainerConfig {

  static {
    SingletonPostgresqlContainer.getInstance();
  }

}
