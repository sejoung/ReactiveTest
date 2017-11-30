package com.github.sejoung.reactive;

import reactor.core.publisher.Flux;

public class ReactorEx {
    public static void main(String[] args) {
        Flux.<Integer>create(e -> {
            e.next(1);
            e.next(2);
            e.next(3);
            e.next(4);
            e.complete();
        })
                .log()
                .map(s -> s * 10)
                .log()
                .reduce(0, (a, b) -> a + b)
                .log()
                .subscribe(System.out::println);
    }
}
