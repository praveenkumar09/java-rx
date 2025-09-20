package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Lec11FluxDefer {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec11FluxDefer.class);

    public static void main(String[] args) {
        Flux.defer(() -> Flux.fromIterable(getNameList(5)));
                //.subscribe(Util.subscriber("subscriber-1"));
    }

    private static Iterable<String> getNameList(int size){
        log.info("getNameList called");
        return IntStream.range(0,size)
                .mapToObj(i -> Util.faker().name().fullName())
                .toList();
    };

}
