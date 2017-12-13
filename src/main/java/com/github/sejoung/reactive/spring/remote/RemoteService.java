package com.github.sejoung.reactive.spring.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RemoteService {

    private static final Logger log = LoggerFactory.getLogger(RemoteService.class);

    @RestController
    public static class MyController {

        @GetMapping("/service")
        public String rest(String req) {
           // Thread.sleep(2000);
            return req+"/service";
        }

        @GetMapping("/service2")
        public String rest2(String req) {
           // Thread.sleep(2000);
            return req+"/service2";
        }
    }

    public static void main(String[] args) {
        System.setProperty("server.port","8081");
        System.setProperty("server.tomcat.max-threads","1000");

        SpringApplication.run(RemoteService.class, args);
    }
}
