package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3,4,5);
        var mono = Mono.fromCallable(() -> findSum(list));
        mono.subscribe(Util.subscriber("subscriber-1"));
    }

    private static int findSum(List<Integer> list){
        log.info("findSum called");
        return list.stream().reduce(0, Integer::sum);
    }
}
