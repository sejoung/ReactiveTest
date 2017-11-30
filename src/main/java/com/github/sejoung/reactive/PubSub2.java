package com.github.sejoung.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PubSub2 {

    public static void main(String[] args) throws InterruptedException {
        //Operators
        // Publisher -> Data1 -> Operator1 -> Data2 -> Operator2 -> Data3 -> Subscriber
        // map (d1 -> d2)

        Publisher<Integer> p = iterPub(Stream.iterate(1, a->a+1).limit(10).collect(Collectors.toList()));
        Publisher<Integer> mapPub = mapPub(p , s -> s* 10);
        Publisher<Integer> mapPub2 = mapPub(mapPub , s -> -s);
        mapPub2.subscribe(logSub());


    }

    private static Publisher<Integer> mapPub(Publisher<Integer> pub, Function<Integer, Integer> f) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber<? super Integer> sub) {
                pub.subscribe(new DelegateSub(sub) {
                    @Override
                    public void onNext(Integer integer) {
                        sub.onNext(f.apply(integer));
                    }
                });
            }
        };
    }


    private static Subscriber<Integer> logSub() {
        return new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(Long.MAX_VALUE);

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("on Next "+integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError "+throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }

    private static Publisher<Integer> iterPub(List<Integer> iter) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber subscriber) {

                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long l) {

                        try{
                            iter.forEach(s->subscriber.onNext(s));
                            subscriber.onComplete();

                        }catch (Throwable t){
                            subscriber.onError(t);
                        }


                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };
    }
}
