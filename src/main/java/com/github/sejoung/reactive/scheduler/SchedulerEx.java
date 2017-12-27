package com.github.sejoung.reactive.scheduler;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SchedulerEx {

    private static final Logger log = LoggerFactory.getLogger(SchedulerEx.class);

    public static void main(String[] args) {

        Publisher<Integer> pub = sub -> {
            sub.onSubscribe(new Subscription() {
                @Override
                public void request(long n) {
                    log.debug("request");
                    sub.onNext(1);
                    sub.onNext(2);
                    sub.onNext(3);
                    sub.onNext(4);
                    sub.onNext(5);
                    sub.onComplete();
                }

                @Override
                public void cancel() {

                }
            });
        };
/*

        Publisher<Integer> subOnPub = sub -> {
            ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory(){
                @Override
                public String getThreadNamePrefix() {
                    return "subOn-";
                }
            });
            es.execute(() -> pub.subscribe(sub));
        };
*/




        Publisher<Integer> pubOnPub = sub -> {

            pub.subscribe(new Subscriber<Integer>() {

                ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory(){
                    @Override
                    public String getThreadNamePrefix() {
                        return "pubOn-";
                    }
                });

                @Override
                public void onSubscribe(Subscription subscription) {
                    sub.onSubscribe(subscription);
                }

                @Override
                public void onNext(Integer integer) {
                    es.execute(() -> sub.onNext(integer));
                }

                @Override
                public void onError(Throwable throwable) {
                    es.execute(() -> sub.onError(throwable));
                    es.shutdown();
                }

                @Override
                public void onComplete() {
                    es.execute(() -> sub.onComplete());
                    es.shutdown();
                }
            });

        };

        pubOnPub.subscribe(new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                log.debug("onSubscribe");
                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.debug("onNext:{}", integer);
            }

            @Override
            public void onError(Throwable throwable) {
                log.debug("onError:{}", throwable);
            }

            @Override
            public void onComplete() {
                log.debug("onComplete");
            }

        });

        log.debug("exit");

    }
}
