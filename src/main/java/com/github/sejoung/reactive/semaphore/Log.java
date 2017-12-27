package com.github.sejoung.reactive.semaphore;

public class Log {
    public static void debug(String strMessage) {
        System.out.println(Thread.currentThread().getName() + " : " + strMessage);
    }
}
