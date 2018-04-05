package com.github.sejoung.reactive.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class TomcatTestApplication {

    @RestController
    public static class MyController {

        @GetMapping("/rest")
        public String rest(String idx) {
            return "test"+idx;
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(TomcatTestApplication.class, args);
    }
}
