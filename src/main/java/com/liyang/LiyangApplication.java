package com.liyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.liyang.mapper")
@Configuration
@EnableTransactionManagement
public class LiyangApplication {
  public static void main(String[] args) {
    SpringApplication.run(LiyangApplication.class, args);
  }

}
