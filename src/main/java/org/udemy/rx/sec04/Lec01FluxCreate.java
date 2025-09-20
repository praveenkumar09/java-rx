package org.udemy.rx.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {

    private static final Logger log = LoggerFactory.getLogger(Lec01FluxCreate.class);

    public static void main(String[] args) {
        log.info("Main method started");
        NameGenerator generator = new NameGenerator();
        Flux.create(generator)
                .subscribe(Util.subscriber("subscriber-1"));
        for (int i = 0; i < 10; i++) {
            generator.generateCountryName();
        }
    }
}
