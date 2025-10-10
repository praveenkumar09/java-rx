package org.udemy.rx.dev.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec08Replay {
    private static final Logger log = LoggerFactory.getLogger(Lec08Replay.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo1();
    }

    private static void demo1(){
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .replay().all();
        Flux<Object> flux = sinkManyOb.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        sinkManyOb
                .tryEmitNext("Hello World");
        sinkManyOb
                .tryEmitNext("Hello World 2");
        sinkManyOb
                .tryEmitNext("Hello World 3");
        Util.sleep(2);
        flux.subscribe(Util.subscriber("jake"));
        sinkManyOb
                .tryEmitNext("Hello World 4");

    }
}
