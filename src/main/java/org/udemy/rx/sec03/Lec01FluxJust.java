package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec01FluxJust {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec01FluxJust.class);

    public static void main(String[] args) {
        Flux
                .just(1,2,3,4,5)
                .subscribe(val -> log.info("got val : {}",val),
                        err -> log.error("error",err),
                        () -> log.info("value passed and subscription completed"));
    }
}
