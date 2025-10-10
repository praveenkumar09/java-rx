package org.udemy.rx.dev.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec04ThreadSafety {
    private static final Logger log = LoggerFactory.getLogger(Lec04ThreadSafety.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo2();
    }

    private static void demo1(){
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .unicast()
                .onBackpressureBuffer();
        Flux<Object> flux = sinkManyOb.asFlux();
        List<Object> list = new ArrayList<>();
        flux.subscribe(list::add);
        for(int i=0; i<1000; i++){
            var j = i;
            CompletableFuture.runAsync(() -> {
                sinkManyOb.tryEmitNext(j);
            });
        }
        Util.sleep(2);
        log.info("list size: {}",list.size());

    }

    private static void demo2(){
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .unicast()
                .onBackpressureBuffer();
        Flux<Object> flux = sinkManyOb.asFlux();
        List<Object> list = new ArrayList<>();
        flux.subscribe(list::add);
        for(int i=0; i<1000; i++){
            var j = i;
            CompletableFuture.runAsync(() -> {
                sinkManyOb.emitNext(j,((signalType, emitResult) -> Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult)));
            });
        }
        Util.sleep(2);
        log.info("list size: {}",list.size());

    }
}
