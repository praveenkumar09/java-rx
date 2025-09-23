package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec06ErrorHandeling {
    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandeling.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorContinue((e,o) -> log.error("Error occurred : {}",e.getMessage()))
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void onErrorReturn(){
        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                //.onErrorReturn(5)
                .onErrorReturn(ArithmeticException.class,-1)
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void onErrorResume(){
        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorResume(e -> onErrorFallback())
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void onErrorComplete(){
        Flux.range(1,10)
                .map(i -> i == 5 ? 5/0 : i)
                .onErrorComplete()
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static Flux<Integer> onErrorFallback(){
        return Flux.just(Util.faker().number().randomDigitNotZero());
    }
}
