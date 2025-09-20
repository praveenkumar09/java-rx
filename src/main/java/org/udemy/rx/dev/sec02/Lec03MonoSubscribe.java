package org.udemy.rx.dev.sec02;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    private static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello World!");
        mono.subscribe(
                i -> log.info("Received value : {}",i),
                err -> log.error("error",err),
                () -> log.info("completed"),
                Subscription::cancel);
    }
}
