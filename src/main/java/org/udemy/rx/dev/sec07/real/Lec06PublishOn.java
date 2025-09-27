package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06PublishOn {

    private static final Logger log = LoggerFactory.getLogger(Lec06PublishOn.class);

    public static void main(String[] args) {
        log.info("Application Main started");
        Flux<Object> flux = Flux.create(sink -> {
                    for (int i = 0; i < 3; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext(val -> log.info("doOnNext : {}", val))
                .doFirst(() -> log.info("doFirst 1 : {}", Thread.currentThread().isVirtual()))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("doFirst 2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("subscriber-1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("subscriber-2"));
        Thread.ofPlatform().start(runnable);
        Thread.ofPlatform().start(runnable2);
        //Thread.ofVirtual().start(runnable2);
        Util.sleep(3);
    }
}
