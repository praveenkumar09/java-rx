package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

public class Lec15Then {
    private static final Logger log = LoggerFactory.getLogger(Lec15Then.class);

    public static void main(String[] args) {
        log.info("Starting application");
        List<String> records = List.of("a", "b", "c");
        saveRecords(records)
                .then(sendEmail(records))
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(10);
    }

    private static Flux<String> saveRecords(List<String> records) {
      return Flux.fromIterable(records)
              .map(r -> "saved record : " + r)
              .delayElements(Duration.ofMillis(500));
    }


    private static Mono<Void> sendEmail(List<String> records) {
        return Mono.fromRunnable(() -> log.info("All these records :{} have been saved", records));
    }
}
