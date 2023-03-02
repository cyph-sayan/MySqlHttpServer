package com.employeeAsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class, scanBasePackages = {"org.employee",
    "com.employeeAsync"})
@EntityScan("org.employee.entity")
@EnableJpaRepositories(basePackages = "org.employee.repository")
public class KafkaServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaServiceApplication.class, args);
  }

}
