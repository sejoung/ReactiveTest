package com.github.sejoung.reactive.future;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallbackFutureTask extends FutureTask<String> {

    SuccesCallback sc;
    ExcepitonCallback ec;

    public CallbackFutureTask(Callable<String> callable, SuccesCallback sc, ExcepitonCallback ec) {
        super(callable);
        this.sc = Objects.requireNonNull(sc);
        this.ec = Objects.requireNonNull(ec);
    }


    @Override
    protected void done() {
        try {
            sc.onSuccess(get());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            ec.onError(e.getCause());
        }
    }
}
