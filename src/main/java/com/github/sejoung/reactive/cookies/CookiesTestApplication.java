package com.github.sejoung.reactive.cookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;

@SpringBootApplication
public class CookiesTestApplication {

    @RestController
    public static class MyController {

        @GetMapping("/rest")
        public String rest() {
            System.out.println("ok ");
            return "ok";
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(CookiesTestApplication.class, args);
    }
}
