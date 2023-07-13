package ru.msa.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan
@EnableJpaRepositories
@SpringBootApplication
public class MsaHomeworkApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsaHomeworkApplication.class, args);
  }

}
