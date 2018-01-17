package com.github.sejoung.reactive.cookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
public class CookiesTestApplication {

    @RestController
    public static class MyController {

        @GetMapping("/rest")
        public String rest(@CookieValue("TEST") String testCookie) {
            System.out.println("ok " + testCookie);
            return "test";
        }

        @GetMapping("/test")
        public void rest1(HttpServletResponse response) {
            response.addCookie(new Cookie("test", "test"));

        }

        @GetMapping("/test2")
        public void rest2(HttpServletResponse response) {
            response.addCookie(new Cookie("test2", "test2"));

        }

    }

    public static void main(String[] args) {
        SpringApplication.run(CookiesTestApplication.class, args);
    }
}
