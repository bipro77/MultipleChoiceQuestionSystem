package com.fireflies.mcqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
//@SpringBootApplication
public class McqsApplication {
    public static void main(String[] args) {
        SpringApplication.run(McqsApplication.class, args);
    }

}
