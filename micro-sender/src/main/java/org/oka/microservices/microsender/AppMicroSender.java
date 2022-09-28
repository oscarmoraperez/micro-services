package org.oka.microservices.microsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AppMicroSender {
    public static void main(String[] args) {
        SpringApplication.run(AppMicroSender.class, args);
    }
}
