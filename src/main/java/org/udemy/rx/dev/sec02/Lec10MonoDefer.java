package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec10MonoDefer {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec10MonoDefer.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5);
        Mono.defer(() -> createPublisher(list))
                .subscribe(Util.subscriber("subscriber-1"));
    }

    private static Mono<Integer> createPublisher(List<Integer> list){
        log.info("createPublisher called");
        Util.sleep(3);
        return Mono.fromSupplier(() -> findSum(list));
    }

    private static int findSum(List<Integer> list){
        log.info("findSum called");
        Util.sleep(5);
        return list.stream().reduce(0, Integer::sum);
    }
}
