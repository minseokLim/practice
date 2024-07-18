package com.minseoklim.websocketpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class WebsocketPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketPracticeApplication.class, args);
    }
}
