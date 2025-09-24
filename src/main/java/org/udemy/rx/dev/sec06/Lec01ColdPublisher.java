package org.udemy.rx.dev.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01ColdPublisher {

    private static final Logger log = LoggerFactory.getLogger(Lec01ColdPublisher.class);

    public static void main(String[] args) {
        Flux<String> flux = Flux.create(sink -> {
            log.info("producer begins");
            for (int i = 0; i < 10; i++) {
                sink.next("message-" + i);
            }
            sink.complete();
        });
        Flux<String> stringFlux = flux.delayElements(Duration.ofSeconds(1));
        stringFlux.subscribe(Util.subscriber("subscriber-1"));
        stringFlux.subscribe(Util.subscriber("subscriber-2"));
        Util.sleep(10);
    }

}
