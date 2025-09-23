package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec07GenerateWithState {

    private static final Logger log = LoggerFactory.getLogger(Lec07GenerateWithState.class);

    public static void main(String[] args) {
        log.info("Main method started");
        implWithState();
    }

    private static void implWithState() {
        Flux.generate(() -> 0,(counter, sink) -> {
                    String countryName = Util.faker().country().name();
                    counter++;
                    if( counter > 10 || countryName.equalsIgnoreCase("canada")) {
                        sink.complete();
                        return counter;
                    }else {
                        sink.next(countryName);
                        return counter;
                    }
                },val -> log.info("Got val: {}",val))
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
