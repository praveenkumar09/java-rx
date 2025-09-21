package org.udemy.rx.dev.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerate {
    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        log.info("Main method started");
        basicImpl();
    }

    private static void basicImpl() {
        Flux.generate(sink -> {
            String countryName = Util.faker().country().name();
            if(countryName.equalsIgnoreCase("canada")) {
                sink.complete();
            }else {
                sink.next(countryName);
            }
        })
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
