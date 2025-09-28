package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01StartWithAssignment {

    private static final Logger log = LoggerFactory.getLogger(Lec01StartWithAssignment.class);

    public static void main(String[] args) {
        log.info("Starting application");
        producer2()
                .startWith(producer1())
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static Flux<Integer> producer1(){
        return Flux.create(sink -> {
            for (int i = 49; i < 54; i++) {
                sink.next(i);
            }
            sink.complete();
        })
                .delayElements(Duration.ofMillis(100))
                .cast(Integer.class);
    }

    private static Flux<Integer> producer2(){
        return Flux.range(0, 4)
                .delayElements(Duration.ofMillis(100))
                .cast(Integer.class);
    }

}
