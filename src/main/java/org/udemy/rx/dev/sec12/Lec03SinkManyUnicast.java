package org.udemy.rx.dev.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec03SinkManyUnicast {
    private static final Logger log = LoggerFactory.getLogger(Lec03SinkManyUnicast.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo1();
    }

    private static void demo1(){
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .unicast()
                .onBackpressureBuffer();
        Flux<Object> flux = sinkManyOb.asFlux();
        sinkManyOb
                .tryEmitNext("Hello World");
        sinkManyOb
                .tryEmitNext("Hello World 2");
        sinkManyOb
                .tryEmitNext("Hello World 3");
        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        Util.sleep(10);

    }
}
