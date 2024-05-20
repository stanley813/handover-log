package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "org.example")
@EnableScheduling
public class HandoverLogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HandoverLogApplication.class, args);
    }

}
