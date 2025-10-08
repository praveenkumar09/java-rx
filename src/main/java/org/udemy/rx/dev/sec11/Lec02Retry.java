package org.udemy.rx.dev.sec11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec02Retry {
    private static final Logger log = LoggerFactory.getLogger(Lec02Retry.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo1();
    }


    private static void demo1(){
        getCountry()
                .retryWhen(Retry
                        .fixedDelay(
                                2,
                                Duration.ofSeconds(1))
                        .filter(throwable -> RuntimeException.class.equals(throwable.getClass()))
                )
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }


    private static void demo3(){
        getCountry()
                .retryWhen(Retry
                        .fixedDelay(
                                2,
                                Duration.ofSeconds(1))
                        .filter(throwable -> throwable.getMessage().contains("Error"))
                )
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static void demo2(){
        getCountry()
                .retryWhen(Retry
                        .fixedDelay(
                                2,
                                Duration.ofSeconds(1)))
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static Mono<String> getCountry(){
        AtomicInteger counter = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if(counter.incrementAndGet() < 3) throw new RuntimeException("Error");
            return Util.faker().country().name();
        })
                .doOnError(throwable -> log.error("Error",throwable.getCause()))
                .doOnSubscribe(s -> log.info("Subscribed"));
    }
}
