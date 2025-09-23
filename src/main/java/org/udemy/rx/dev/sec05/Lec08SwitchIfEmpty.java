package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {
    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .filter(i -> i > 11)
                .switchIfEmpty(switchToFallback())
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static Flux<Integer> switchToFallback(){
        return Flux.range(100,3);
    }
}
