package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec07DefaultIfEmpty {
    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultIfEmpty.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .filter(i -> i > 11)
                .defaultIfEmpty(1)
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
