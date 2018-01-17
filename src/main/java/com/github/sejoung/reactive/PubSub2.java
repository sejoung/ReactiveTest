package com.github.sejoung.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PubSub2 {

    public static void main(String[] args) {
        //Operators
        // Publisher -> Data1 -> Operator1 -> Data2 -> Operator2 -> Data3 -> Subscriber
        // map (d1 -> d2)

        Publisher<Integer> pub = iterPub(Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));
       // Publisher<String> mapPub = mapPub(pub, s -> "[" + s + "]");
        /// Publisher<Integer> sumPub = sumPub(pub);
        Publisher<StringBuilder> reducePub = reducePub(pub, new StringBuilder(), (a, b)->a.append(b+","));

        reducePub.subscribe(logSub());

        abc:{
            System.out.println("1");
            if(2==1)
            break abc;
            System.out.println("2");

        }

        if (2 ==1){

        }else for (int i=0 ; i<10 ; i++){

            System.out.println(i);
        }

    }

    private static <T,R> Publisher<R> reducePub(Publisher<T> pub, R init, BiFunction<R, T, R> bf) {
        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> sub) {
                pub.subscribe(new DelegateSub<T, R>(sub) {
                    R result = init;

                    @Override
                    public void onNext(T t) {
                        result = bf.apply(result, t);
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(result);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static Publisher<Integer> sumPub(Publisher<Integer> pub) {
        return new Publisher<Integer>() {
            @Override
            public void subscribe(Subscriber sub) {
                pub.subscribe(new DelegateSub<Integer, Integer>(sub) {

                    int sum = 0;

                    @Override
                    public void onNext(Integer t) {
                        sum += t;
                    }

                    @Override
                    public void onComplete() {
                        sub.onNext(sum);
                        sub.onComplete();
                    }
                });
            }
        };
    }

    private static <T, R> Publisher<R> mapPub(Publisher<T> pub, Function<T, R> f) {
        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> sub) {
                pub.subscribe(new DelegateSub<T, R>(sub) {
                    @Override
                    public void onNext(T t) {
                        sub.onNext(f.apply(t));
                    }
                });
            }
        };
    }


    private static <T> Subscriber<T> logSub() {
        return new Subscriber<T>() {

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(Long.MAX_VALUE);

            }

            @Override
            public void onNext(T t) {
                System.out.println("on Next " + t);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
    }

    private static <T> Publisher<T> iterPub(List<T> iter) {
        return new Publisher<T>() {
            @Override
            public void subscribe(Subscriber subscriber) {

                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long l) {

                        try {
                            iter.forEach(s -> subscriber.onNext(s));
                            subscriber.onComplete();

                        } catch (Throwable t) {
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
