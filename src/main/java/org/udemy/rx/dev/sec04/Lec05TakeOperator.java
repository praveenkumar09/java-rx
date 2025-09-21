package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {
    private static final Logger log = LoggerFactory.getLogger(Lec05TakeOperator.class);

    public static void main(String[] args) {
        log.info("Main method started");
        //takeOp();
        //takeWhileOp();
        takeUntilOp();
    }

    private static void takeOp() {
        Flux.range(1,10)
                .log("take")
                .take(5)
                .log("sub")
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void takeWhileOp() {
        Flux.range(1,10)
                .log("take")
                .takeWhile(i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static void takeUntilOp() {
        Flux.range(1,10)
                .log("take")
                .takeUntil(i -> i < 5)
                .log("sub")
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
