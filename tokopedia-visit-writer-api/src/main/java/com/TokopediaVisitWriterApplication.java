package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class TokopediaVisitWriterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TokopediaVisitWriterApplication.class, args);
    }
}