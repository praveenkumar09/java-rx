package org.udemy.rx.dev.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {
    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        log.info("Starting Lec01Context");
        getCountry()
                .contextWrite(ctx -> ctx.put("country", "USA"))
                .contextWrite(Context.of("country", "Brazil"))
                .subscribe(Util
                        .subscriber("subscriber-1"));
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
}
