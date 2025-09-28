package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03ConcatWith {
    private static final Logger log = LoggerFactory.getLogger(Lec03ConcatWith.class);

    public static void main(String[] args) {
        log.info("Starting application");
        getEvenNumbers()
                .concatWith(getOddNumbers())
                .concatWithValues(0)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
        Flux.concat(getEvenNumbers(), getOddNumbers())
                .subscribe(Util.subscriber("subscriber-2"));
        Util.sleep(3);
    }

    private static Flux<Integer> getEvenNumbers() {
        return Flux.range(1, 10)
                .filter(i -> i % 2 == 0)
                .doOnSubscribe(s -> log.info("Subscribe to Producer-1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> getOddNumbers() {
        return Flux.range(1, 10)
                .filter(i -> i % 2 != 0)
                .doOnSubscribe(s -> log.info("Subscribe to Producer-2"))
                .delayElements(Duration.ofMillis(10));
    }

}
