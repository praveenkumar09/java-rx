package org.udemy.rx.dev.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

public class Lec03ContextPropagation {
    private static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);


    public static void main(String[] args) {
        log.info("Starting Lec01Context");
        getCountry()
                .concatWith(Flux
                        .merge(getProducer1(),
                                getProducer2()
                                        .contextWrite(ctx -> Context.empty())))
                .contextWrite(Context.of("country", "Brazil"))
                .subscribe(Util
                        .subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static Mono<String> getCountry(){
        return Mono
                .deferContextual(ctx -> {
                    log.info("Context: {}", ctx);
                    if(ctx.hasKey("country"))
                        return Mono.just("Welcome %s".formatted(ctx.get("country").toString()));
                    else
                        return Mono.error(new RuntimeException("Country not found"));
                });
    }

    private static Mono<String> getProducer1(){
        return Mono
                .deferContextual(ctx -> {
                    log.info("getProducer1: {}", ctx);
                    if(ctx.hasKey("country"))
                        return Mono.just("Welcome %s".formatted(ctx.get("country").toString()));
                    else
                        return Mono.error(new RuntimeException("Country not found"));
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> getProducer2(){
        return Mono
                .deferContextual(ctx -> {
                    log.info("getProducer2: {}", ctx);
                    if(ctx.hasKey("country"))
                        return Mono.just("Welcome %s".formatted(ctx.get("country").toString()));
                    else
                        return Mono.error(new RuntimeException("Country not found"));
                })
                .subscribeOn(Schedulers.parallel());
    }
}
