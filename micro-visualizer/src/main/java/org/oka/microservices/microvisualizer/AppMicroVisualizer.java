package org.oka.microservices.microvisualizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class AppMicroVisualizer {
    public static void main(String[] args) {
        SpringApplication.run(AppMicroVisualizer.class, args);
    }
}
