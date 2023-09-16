package com.tonde.maisonchapback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MaisonChapBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaisonChapBackApplication.class, args);
    }


}
