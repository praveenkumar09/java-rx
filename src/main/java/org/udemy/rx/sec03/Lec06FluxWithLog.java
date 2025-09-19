package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxWithLog {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec06FluxWithLog.class);

    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .subscribe(Util.subscriber("subscriber-1"));
    }

}
