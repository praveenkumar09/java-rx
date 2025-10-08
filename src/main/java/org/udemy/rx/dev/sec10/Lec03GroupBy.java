package org.udemy.rx.dev.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03GroupBy {
    private static final Logger log = LoggerFactory.getLogger(Lec03GroupBy.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2 == 0 ? "even" : "odd")
                .flatMap(Lec03GroupBy::processEvents)
                        .subscribe();
        Util.sleep(60);
    }

    private static Mono<Void> processEvents(GroupedFlux<String, Integer> groupedFlux){
        log.info("Processing events for group : {}", groupedFlux.key());
        return groupedFlux
                .doOnNext(i -> log.info("Processing event : key : {}, value : {}",groupedFlux.key(),i))
                .doOnComplete(() -> log.info("Completed processing events for group : {}", groupedFlux.key()))
                .then();
    }


}
