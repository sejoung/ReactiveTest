package com.github.sejoung.reactive.future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class FutureEx {
    private static final Logger log = LoggerFactory.getLogger(FutureEx.class);

    // Future
    // Callback

    public static void main(String[] args) {
        //맥시멈 제한이 없고 처음엔 0개 생성
        ExecutorService es = Executors.newCachedThreadPool();


        CallbackFutureTask f = new CallbackFutureTask(() -> {
            Thread.sleep(2000);
            if(1 == 1) throw new RuntimeException("Async Error");
            log.debug("async");
            return "hello";
        },
          s -> log.debug(s),
                e -> log.debug(e.getMessage())
        );

        /*
        FutureTask<String> f = new FutureTask<String>(() -> {
            Thread.sleep(2000);
            log.debug("async");
            return "hello";
        }){
            @Override
            protected void done() {
                try {
                    log.debug(get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
*/
        es.execute(f);
        es.shutdown();

/*

        Future<String> f = es.submit(() -> {
            Thread.sleep(2000);
            log.debug("async");
            return "hello";
        });
*/

        //blocking, Non-Blocking
       /*
        log.debug(f.isDone()+"");
        Thread.sleep(2100);
        log.debug("exit");
        log.debug(f.isDone()+"");
        log.debug(f.get());
*/
    }
}
