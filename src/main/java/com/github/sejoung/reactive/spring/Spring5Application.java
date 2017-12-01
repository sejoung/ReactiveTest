
package com.github.sejoung.reactive.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author kim se joung
 *
 */
@SpringBootApplication
public class Spring5Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Spring5Application.class, args);
    }

}
