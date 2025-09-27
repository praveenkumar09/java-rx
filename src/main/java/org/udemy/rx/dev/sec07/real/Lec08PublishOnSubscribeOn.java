package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08PublishOnSubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(Lec08PublishOnSubscribeOn.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux<Object> flux = Flux.create(sink -> {
                    for (int i = 0; i < 2; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .subscribeOn(Schedulers.parallel())
                .doOnNext(val -> log.info("doOnNext : {}", val))
                .doFirst(() -> log.info("doFirst 1 : {}", Thread.currentThread().isVirtual()))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("doFirst 2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("subscriber-1"));
        Thread.ofPlatform().start(runnable);
        Util.sleep(3);
    }
}
