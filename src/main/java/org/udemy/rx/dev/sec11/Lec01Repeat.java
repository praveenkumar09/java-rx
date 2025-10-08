package org.udemy.rx.dev.sec11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec01Repeat {
    private static final Logger log = LoggerFactory.getLogger(Lec01Repeat.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo4();
    }

    private static void demo1(){
        getCountry()
                .repeat(3)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static void demo2(){
        getCountry()
                .repeat()
                .takeUntil(s -> s.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static void demo3(){
        AtomicInteger counter = new AtomicInteger(0);
        getCountry()
                .repeat(() -> counter.incrementAndGet() < 3)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static void demo4(){
        getCountry()
                .repeatWhen(f -> f
                        .delayElements(Duration.ofSeconds(2))
                        .take(2)
                )
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(20);
    }

    private static Mono<String> getCountry(){
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
