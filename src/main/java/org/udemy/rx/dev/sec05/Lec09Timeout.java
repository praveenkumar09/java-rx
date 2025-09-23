package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec09Timeout {
    private static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {
        log.info("Application started");
        getProductName()
                .timeout(Duration.ofSeconds(2),getFallbackProductName())
                .onErrorReturn("fallback")
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(12);
    }

    private static Flux<String> getProductName(){
        return Flux.defer(() -> Flux.just("iPhone", "MacBook Pro", "Samsung Galaxy S10"))
                .delayElements(Duration.ofSeconds(3));
    }

    private static Flux<String> getFallbackProductName(){
        return Flux.defer(() -> Flux.just(Util.faker().country().name()))
                .delayElements(Duration.ofSeconds(1));
    }
}
