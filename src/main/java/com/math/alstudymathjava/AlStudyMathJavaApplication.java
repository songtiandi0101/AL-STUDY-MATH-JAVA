package com.math.alstudymathjava;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.math.alstudymathjava.system.mapper")
public class AlStudyMathJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlStudyMathJavaApplication.class, args);
    }

}
