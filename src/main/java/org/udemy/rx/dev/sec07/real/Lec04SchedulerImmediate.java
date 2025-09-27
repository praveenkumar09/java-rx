package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04SchedulerImmediate {

    private static final Logger log = LoggerFactory.getLogger(Lec04SchedulerImmediate.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux<Object> flux = Flux.create(sink -> {
                    for (int i = 0; i < 3; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .subscribeOn(Schedulers.immediate())
                .doOnNext(val -> log.info("doOnNext : {}", val))
                .doFirst(() -> log.info("doFirst 1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("doFirst 2"));

        Runnable runnable = () -> flux.subscribe(Util.subscriber("subscriber-1"));
        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("subscriber-2"));
        Thread.ofVirtual().start(runnable);
        Thread.ofVirtual().start(runnable2);
        Util.sleep(3);
    }
}
