package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {

    private static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Flux.merge(getEvenNumbers(), getOddNumbers(), get11to20())
                .take(2)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
        getEvenNumbers()
                .mergeWith(getOddNumbers())
                .mergeWith(get11to20())
                .take(2)
                .subscribe(Util.subscriber("subscriber-2"));
        Util.sleep(3);
    }

    private static Flux<Integer> getEvenNumbers() {
        return Flux.range(1, 10)
                .filter(i -> i % 2 == 0)
                .transform(Util.fluxLogger("getEvenNumbers"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> getOddNumbers() {
        return Flux.range(1, 10)
                .filter(i -> i % 2 != 0)
                .transform(Util.fluxLogger("getOddNumbers"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> get11to20() {
        return Flux.range(11, 10)
                .transform(Util.fluxLogger("get11T020"))
                .delayElements(Duration.ofMillis(10));
    }

}
