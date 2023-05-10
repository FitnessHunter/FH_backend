package com.softlex.fh.container;

import org.testcontainers.containers.PostgreSQLContainer;

public class SingletonPostgresqlContainer extends
    PostgreSQLContainer<SingletonPostgresqlContainer> {

  private static final String IMAGE_VERSION = "postgres:14.1";
  private static SingletonPostgresqlContainer container;

  private SingletonPostgresqlContainer() {
    super(IMAGE_VERSION);
  }

  public static SingletonPostgresqlContainer getInstance() {
    if (container == null) {
      container = new SingletonPostgresqlContainer();
    }
    container.start();
    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("spring.datasource.url", container.getJdbcUrl());
    System.setProperty("spring.datasource.username", container.getUsername());
    System.setProperty("spring.datasource.password", container.getPassword());
  }

  //    @DynamicPropertySource
//    static void postgresqlProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.liquibase.contexts", () -> "!prod");
//    }
  @Override
  public void stop() {
    //do nothing, JVM handles shut down
  }
}
