package org.udemy.rx.dev.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec10FluxEmptyError.class);

    public static void main(String[] args) {
        log.info("Lec10FluxEmptyError main method");
        Flux.empty()
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(1);
        Flux.error(new RuntimeException("error"))
                .subscribe(Util.subscriber("subscriber-2"));
    }

}
