package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec05Subscribe {
    private static final Logger log = LoggerFactory.getLogger(Lec05Subscribe.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .doOnNext(val -> log.info("doOnNext : {}", val))
                .doOnComplete(() -> log.info("doOnComplete"))
                .doOnError(err -> log.info("doOnError : {}", err.getMessage()))
                .subscribe();
    }
}
