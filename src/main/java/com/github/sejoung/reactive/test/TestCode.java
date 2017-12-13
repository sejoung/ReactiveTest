package com.github.sejoung.reactive.test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestCode {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();

        int count = 100;
        AtomicInteger ai = new AtomicInteger();
        CyclicBarrier cb = new CyclicBarrier(count);

        for (int i = 0; i < count; i++) {
            es.submit(() -> {
                int idx = ai.addAndGet(1);
                cb.await();
                FreqABTest cTest = new FreqABTest();        /// C
                FreqABTest.setFreqAbtestType(idx);

                if ((idx != FreqABTest.getFreqAbtestType())) {
                    System.out.println(" idx " + idx + " freqAbtestType " + FreqABTest.getFreqAbtestType());
                }
                return null;
            });

        }
        es.shutdown();
        es.awaitTermination(10000, TimeUnit.HOURS);

    }
}
