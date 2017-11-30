package com.github.sejoung.reactive;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
       /*

       Iterable<Integer> iter = () ->
                new Iterator<Integer>() {
                    int i = 0;
                    final static int MAX = 10;

                    @Override
                    public boolean hasNext() {
                        return i < MAX;
                    }

                    @Override
                    public Integer next() {
                        return ++i;
                    }
                };


        for (Integer i : iter) {
            System.out.println(i);
        }

        for (Iterator<Integer> it = iter.iterator(); it.hasNext() ;) {
            System.out.println(it.next());
        }
*/

// Source -> Event/Data -> Observer


        Observer ob = (Observable o, Object arg) -> {
                System.out.println(Thread.currentThread().getName() + " " + arg);
        };

        IntObservable io = new IntObservable();
        io.addObserver(ob);
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io);
        System.out.println(Thread.currentThread().getName() + " EXIT ");
        es.shutdown();

    }


    static class IntObservable extends Observable implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                setChanged();
                notifyObservers(i);
            }
        }
    }
}
