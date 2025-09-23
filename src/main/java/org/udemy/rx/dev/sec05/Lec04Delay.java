package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    private static final Logger log = LoggerFactory.getLogger(Lec04Delay.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(12);
    }
}
