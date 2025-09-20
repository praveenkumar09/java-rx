package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

public class Lec09CreateVsExecution {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec09CreateVsExecution.class);

    public static void main(String[] args) {
        getName()
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static Mono<String> getName(){
        log.info("getName called");
        return Mono.fromSupplier(Util::faker)
                .map(faker -> faker.name().fullName());
    }
}
