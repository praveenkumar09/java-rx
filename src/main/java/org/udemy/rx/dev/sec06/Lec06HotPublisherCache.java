package org.udemy.rx.dev.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06HotPublisherCache {
    private static final Logger log = LoggerFactory.getLogger(Lec06HotPublisherCache.class);

    public static void main(String[] args) {
        log.info("Hot Publisher With Cache Demo");
        Flux<String> stockStreamFlux = stockStream().replay(1).autoConnect(0);
        Util.sleep(4);
        log.info("Sam joined");
        stockStreamFlux
                .take(2)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(4);
        log.info("Mike joined");
        stockStreamFlux
                .take(5)
                .subscribe(Util.subscriber("subscriber-2"));
        Util.sleep(15);
    }

    private static Flux<String> stockStream() {
        return Flux
                .generate(
                        (sink) -> {
                            sink.next("Stock Price : " + Util.faker().random().nextInt(10,100));
                        }
                )
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("Emitting : {}", price))
                .cast(String.class);
    }
}
