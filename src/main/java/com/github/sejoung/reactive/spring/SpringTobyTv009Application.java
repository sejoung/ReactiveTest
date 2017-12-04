
package com.github.sejoung.reactive.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author kim se joung
 */
@SpringBootApplication
public class SpringTobyTv009Application {
    private static final Logger log = LoggerFactory.getLogger(SpringTobyTv009Application.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringTobyTv009Application.class, args);
    }


}
