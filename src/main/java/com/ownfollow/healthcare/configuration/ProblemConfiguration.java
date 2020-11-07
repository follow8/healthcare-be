package com.ownfollow.healthcare.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@Configuration
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class ProblemConfiguration {
  @Bean
  public ObjectMapper jacksonObjectMapper() {
    return new ObjectMapper().registerModules(
        new ProblemModule(),
        new ConstraintViolationProblemModule()
    );
  }
}
