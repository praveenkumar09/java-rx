package org.udemy.rx.dev.sec08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec04FluxCreateProblem{

    private static final Logger log = LoggerFactory.getLogger(Lec04FluxCreateProblem.class);

    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small","16");
        Flux<Integer> flux = Flux.create(sink -> {
            for (int i = 0; i <= 500 && !sink.isCancelled(); i++) {
                log.info("generating state : {}", i);
                sink.next(i);
                Util.sleepMilliSeconds(50);
            }
            sink.complete();
                })
                .subscribeOn(Schedulers.parallel())
                .cast(Integer.class);

        flux
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreateProblem::timeConsumingOperation)
                .subscribe();
        Util.sleep(60);

    }

    private static int timeConsumingOperation(int i) {
        log.info("Processing {}", i);
        Util.sleep(1);
        return i;
    }

}
