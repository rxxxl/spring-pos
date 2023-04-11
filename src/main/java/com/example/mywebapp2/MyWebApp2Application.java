package com.example.mywebapp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MyWebApp2Application {

    public static void main(String[] args) {
        SpringApplication.run(MyWebApp2Application.class, args);
    }

}
