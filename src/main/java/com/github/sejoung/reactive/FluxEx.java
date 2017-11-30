package com.github.sejoung.reactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FluxEx {
    private static final Logger log = LoggerFactory.getLogger(FluxEx.class);

    public static void main(String[] args) throws InterruptedException {
        //컨슈머가 느릴때 publishOn 사용
        /*
        Flux.range(1,10)
                .publishOn(Schedulers.newSingle("pub"))
                .log()
                .subscribeOn(Schedulers.newSingle("sub"))
                .subscribe(System.out::println);




*/
/*


        Executors.newSingleThreadExecutor().execute(()->{
            try{
                TimeUnit.SECONDS.sleep(2);

            }catch (InterruptedException e){}
            log.debug("hell");
        });

*/

        //user, demon

        Flux.interval(Duration.ofMillis(200))
                .take(10)
                .subscribe(s -> log.debug("next:{}",s));

        TimeUnit.SECONDS.sleep(10);

        log.debug("exit");
    }
}
