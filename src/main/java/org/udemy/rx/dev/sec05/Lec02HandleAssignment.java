package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {
    private static final Logger log = LoggerFactory.getLogger(Lec02HandleAssignment.class);

    public static void main(String[] args) {
        log.info("Application started");
        Flux.<String>generate(sink -> {
            sink.next(Util.faker().country().name());
        })
                .handle((countryName, sink) -> {
                    if(countryName.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }else {
                        sink.next(countryName);
                    }
                })
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
