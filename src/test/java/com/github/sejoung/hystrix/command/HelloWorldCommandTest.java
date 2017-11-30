package com.github.sejoung.hystrix.command;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class HelloWorldCommandTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldCommandTest.class);

    @Test
    public void execute() {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand(" World");
        assertEquals("Hello World", helloWorldCommand.execute());
    }

    @Test
    public void queue() throws ExecutionException, InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand(" World");
        Future<String> future = helloWorldCommand.queue();
        assertEquals("Hello World", future.get());
    }

    @Test
    public void rxjava() throws ExecutionException, InterruptedException {
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand(" World");
        CountDownLatch l = new CountDownLatch(1);
        Observable<String> obs = helloWorldCommand.observe();
        obs.subscribe(
                s -> logger.info("Received : " + s),
                t -> logger.error(t.getMessage(), t),
                () -> l.countDown()
        );
        l.await(5, TimeUnit.SECONDS);
    }
}
