package org.udemy.rx.sec03;

import org.slf4j.Logger;
import reactor.core.publisher.Flux;

public class Lec02FluxMultipleSubscribers {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec02FluxMultipleSubscribers.class);

    public static void main(String[] args) {
        var flux = Flux.just(1,2,3,4,5);
        flux.subscribe(i -> log.info("subscriber-1 received value : {}",i));
        flux.subscribe(i -> log.info("subscriber-2 received value : {}",i));
        flux
                .filter(i -> i%2 == 0)
                .map(i -> i*2)
                .subscribe(i -> log.info("subscriber-3 received value : {}",i));
    }
}
