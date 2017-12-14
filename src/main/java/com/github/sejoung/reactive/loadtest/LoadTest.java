package com.github.sejoung.reactive.loadtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class LoadTest {
    static AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(LoadTest.class);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        int cnt = 100;
        ExecutorService es = Executors.newFixedThreadPool(cnt);

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8080/rest?idx={idx}";
        CyclicBarrier barrier = new CyclicBarrier(cnt);
        for (int i = 0; i < cnt ; i++) {
            es.submit(()->{
                int idx = counter.addAndGet(1);
                barrier.await();
                log.info("Thread {}",idx);
                StopWatch sw = new StopWatch();
                sw.start();
                String res = rt.getForObject(url,String.class,idx);
                sw.stop();
                log.info("Elapsed : {} {} / {}",idx,sw.getTotalTimeSeconds(),res);
                return null;
            });
        }
        barrier.await();
        StopWatch main = new StopWatch();
        main.start();

        es.shutdownNow();
        es.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();

        log.info("Elapsed main : {}",main.getTotalTimeSeconds());

    }
}
