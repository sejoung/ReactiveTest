package com.github.sejoung.reactive.semaphore;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreResource extends Semaphore {

    private static final Random rd = new Random(10000);

    public SemaphoreResource(final int permits) {
        super(permits);
    }

    public void use() throws InterruptedException {

        acquire(); // 세마포어 리소스 확보

        try {
            doUse();
        } finally {
            release(); // 세마포어 리소스 해제
            Log.debug("Thread 종료 후 남은  permits: " + this.availablePermits());
        }
    }

    protected void doUse() throws InterruptedException {

        // 임의의 프로그램을 실행하는데 거리는 가상의 시간
        int sleepTime = rd.nextInt(500);
        Thread.sleep(sleepTime); // 런타임 시간 설정
        Log.debug(" Thread 실행..................." + sleepTime);
        /** something logic **/

    }

}
