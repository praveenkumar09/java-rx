package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec04FluxFromStreams {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec04FluxFromStreams.class);

    public static void main(String[] args) {
        log.info("Lec04FluxFromStreams main method");
        var list  = List.of(1,2,3,4,5);
        //var stream = list.stream();
        Flux<Integer> flux = Flux.fromStream(list::stream);
        flux
                .subscribe(Util.subscriber("subscriber-1"));
        // not possible as stream will be closed and can be consumed only once
        //flux.subscribe(Util.subscriber("subscriber-2"));
        // instead need to change like this
        flux
                .subscribe(Util.subscriber("subscriber-2"));
    }
}
