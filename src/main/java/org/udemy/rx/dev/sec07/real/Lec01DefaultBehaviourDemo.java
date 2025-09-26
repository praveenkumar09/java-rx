package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec01DefaultBehaviourDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviourDemo.class);

    public static void main(String[] args) throws InterruptedException {
        log.info("Application started");
        Flux<Object> flux = Flux.create(sink -> {
                    for (int i = 0; i < 3; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext(val -> log.info("doOnNext : {}", val));
        Runnable runnable = () -> flux.subscribe(Util.subscriber("subscriber-1"));
        Thread.ofVirtual().start(runnable).join();
    }
}
