package org.udemy.rx.dev.sec06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec07FluxCreateIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec07FluxCreateIssueFix.class);

    public static void main(String[] args) {
        log.info("Main method started");
        NameGenerator generator = new NameGenerator();
        Flux<String> flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("subscriber-1"));
        flux.subscribe(Util.subscriber("subscriber-2"));
        for (int i = 0; i < 10; i++) {
            generator.generateCountryName();
        }
    }
}
