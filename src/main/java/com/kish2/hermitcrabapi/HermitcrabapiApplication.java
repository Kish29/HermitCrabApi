package com.kish2.hermitcrabapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kish2.hermitcrabapi.mapper")
public class HermitcrabapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HermitcrabapiApplication.class, args);
    }

}
