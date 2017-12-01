package com.github.sejoung.reactive.interval;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IntervalEx {
    private static final Logger log = LoggerFactory.getLogger(IntervalEx.class);

    public static void main(String[] args) {


        Publisher<Integer> pub = sub -> {
            sub.onSubscribe(new Subscription() {
                int no = 0;
                boolean cancelled = false;

                @Override
                public void request(long l) {
                    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
                    exec.scheduleAtFixedRate(() -> {
                        if(cancelled){
                            exec.shutdown();
                            return;
                        }
                        sub.onNext(no++);
                    }, 0, 300, TimeUnit.MILLISECONDS);
                }

                @Override
                public void cancel() {
                    cancelled = true;
                }
            });
        };


        Publisher<Integer> takePub = sub -> {
            pub.subscribe(new Subscriber<Integer>() {

                int count = 0;
                Subscription subscription;

                @Override
                public void onSubscribe(Subscription subscription) {
                    sub.onSubscribe(subscription);
                    this.subscription = subscription;
                }

                @Override
                public void onNext(Integer integer) {
                    sub.onNext(integer);
                    if(++count >= 5){
                        subscription.cancel();
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    sub.onError(throwable);
                }

                @Override
                public void onComplete() {
                    sub.onComplete();
                }
            });
        };



        takePub.subscribe(new Subscriber<Integer>() {

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

    }
}
