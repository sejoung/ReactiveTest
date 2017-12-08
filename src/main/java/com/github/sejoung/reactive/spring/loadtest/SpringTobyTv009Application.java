
package com.github.sejoung.reactive.spring.loadtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kim se joung
 */
@SpringBootApplication
public class SpringTobyTv009Application {
    private static final Logger log = LoggerFactory.getLogger(SpringTobyTv009Application.class);

    @RestController
    public static class MyController {

        @GetMapping("/rest")
        public String rest(int idx) {
            return "rest "+ idx;
        }
    }

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SpringTobyTv009Application.class, args);
    }


}
