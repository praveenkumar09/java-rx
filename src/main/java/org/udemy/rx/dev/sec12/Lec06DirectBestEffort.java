package org.udemy.rx.dev.sec12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec06DirectBestEffort {
    private static final Logger log = LoggerFactory.getLogger(Lec06DirectBestEffort.class);

    public static void main(String[] args) {
        log.info("Starting application");
        demo2();
    }

    private static void demo1(){
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .multicast()
                .onBackpressureBuffer();
        Flux<Object> flux = sinkManyOb.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("mike"));
        for(int i=0; i<100; i++){
            Sinks.EmitResult emitResult = sinkManyOb.tryEmitNext(i);
            log.info("item : {}, emitResult: {}",i,emitResult);
        }
        Util.sleep(100);

    }

    private static void demo2(){
        System.setProperty("reactor.bufferSize.small", "16");
        Sinks.Many<Object> sinkManyOb = Sinks.many()
                .multicast()
                .directBestEffort();
        Flux<Object> flux = sinkManyOb.asFlux();
        flux.subscribe(Util.subscriber("sam"));
        flux
                .onBackpressureBuffer()
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("mike"));
        for(int i=0; i<100; i++){
            Sinks.EmitResult emitResult = sinkManyOb.tryEmitNext(i);
            log.info("item : {}, emitResult: {}",i,emitResult);
        }
        Util.sleep(100);

    }
}
