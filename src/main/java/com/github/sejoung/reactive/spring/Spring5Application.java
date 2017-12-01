
package com.github.sejoung.reactive.spring;

import com.github.sejoung.reactive.future.FutureEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @author kim se joung
 */
@SpringBootApplication
@EnableAsync
public class Spring5Application {
    private static final Logger log = LoggerFactory.getLogger(Spring5Application.class);

    @Component
    public static class MyService {
        @Async
        public ListenableFuture<String> hello() throws InterruptedException {
            log.debug("hello()");
            Thread.sleep(2000);
            return new AsyncResult<>("Hello");
        }
    }


    public static void main(String[] args) throws Exception {
        try (ConfigurableApplicationContext c = SpringApplication.run(Spring5Application.class, args)) {

        }
    }

    @Autowired
    MyService myService;

    @Bean
    ApplicationRunner run() {
        return args -> {
            log.debug("run");
            ListenableFuture<String> s = myService.hello();
            s.addCallback(a -> log.debug(a), e -> log.debug(e.getMessage()));
            log.debug("exit");
        };

    }
}
