package com.github.sejoung.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DelegateSub implements Subscriber<Integer> {
    Subscriber sub;

    public DelegateSub(Subscriber sub){
        this.sub = sub;
    }
    @Override
    public void onSubscribe(Subscription subscription) {
        sub.onSubscribe(subscription);
    }

    @Override
    public void onNext(Integer integer) {
        sub.onNext(integer);
    }

    @Override
    public void onError(Throwable throwable) {

        sub.onError(throwable);
    }

    @Override
    public void onComplete() {
        sub.onComplete();
    }
}
