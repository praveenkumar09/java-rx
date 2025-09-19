package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec03FluxFromIterableOrArray {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec03FluxFromIterableOrArray.class);

    public static void main(String[] args) {

        var list = List.of(1,2,3,4,5);
        Flux
                .fromIterable(list)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(5);
        Flux
                .fromArray(list.toArray())
                .subscribe(Util.subscriber("subscriber-2"));
    }
}
