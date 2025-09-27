package org.udemy.rx.dev.sec08;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribers {

    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.generate(() -> 1, (state, sink) -> {
                    log.info("generating state : {}", state);
                    sink.next(state);
                    return state + 1;
                }, (state) -> log.info("state : {}", state))
                .subscribeOn(Schedulers.parallel())
                .cast(Integer.class);

        flux
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscribers::timeConsumingOperation)
                .subscribe(Util.subscriber("subscriber-1"));

        flux
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("subscriber-2"));

        Util.sleep(60);

    }

    private static int timeConsumingOperation(int i) {
        Util.sleep(1);
        return i;
    }
}
