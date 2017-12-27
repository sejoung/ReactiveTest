package com.github.sejoung.reactive.semaphore;

public class SemaphoreTest {

    public static void main(String[] args) {
        System.out.println("Test Start...");
        SemaphoreResource resource = new SemaphoreResource(4);
        for (int i = 0; i < 20; i++) {
            new MyThread("Thread-" + i, resource).start();
        }
        System.out.println("Test Stop...");
    }

}
