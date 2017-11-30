package com.github.sejoung.hystrix.command;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class HelloWorldObservableCommandTest {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorldObservableCommandTest.class);

    @Test
    public void observe() throws InterruptedException {
        HelloWorldObservableCommand helloWorldCommand = new HelloWorldObservableCommand("World");
        logger.info("Completed executing HelloWorld Command");
        CountDownLatch l = new CountDownLatch(1);
        Observable<String> obs = helloWorldCommand.observe();
        obs.subscribe(
                s -> logger.info("Received : " + s),
                t -> logger.error(t.getMessage(), t),
                () -> l.countDown()
        );
        l.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void toObservable() throws InterruptedException {
        HelloWorldObservableCommand helloWorldCommand = new HelloWorldObservableCommand("World");
        logger.info("Completed executing HelloWorld Command");
        CountDownLatch l = new CountDownLatch(1);
        Observable<String> obs = helloWorldCommand.toObservable();
        obs.subscribe(
                s -> logger.info("Received : " + s),
                t -> logger.error(t.getMessage(), t),
                () -> l.countDown()
        );
        l.await(5, TimeUnit.SECONDS);

    }
}
