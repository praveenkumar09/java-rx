package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import reactor.core.publisher.Mono;

import static org.udemy.rx.common.Util.subscriber;

public class Demo {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Demo.class);
    public static void main(String[] args) {
        Mono<String> mono = Mono.just("Hello World!");
        mono.subscribe(subscriber("sub1"));
        mono.subscribe(subscriber("sub2"));
    }
}
