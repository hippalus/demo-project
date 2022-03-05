package org.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableJdbcAuditing
@SpringBootApplication
public class DemoProjectApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoProjectApplication.class, args);
  }

}
