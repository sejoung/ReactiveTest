package com.github.sejoung.reactive.semaphore;

public class MyThread extends Thread {

    private final SemaphoreResource resource;

    public MyThread(String threadName, SemaphoreResource resource) {
        this.resource = resource;
        this.setName(threadName);
    }

    @Override
    public void run() {
        try {
            resource.use();
        } catch (InterruptedException e) {
        } finally {
        }
    }

}