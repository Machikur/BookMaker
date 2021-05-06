package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class BookMakerStarter {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Poland"));
        SpringApplication.run(BookMakerStarter.class, args);
    }

}
