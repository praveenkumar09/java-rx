package org.udemy.rx.dev.sec13.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {
    private static final Logger log = LoggerFactory.getLogger(RateLimiter.class);
    private static final Map<String, Integer> categoryAttempts =
            Collections.synchronizedMap(new HashMap<>());

    static {
        refresh();
    }

    static <T> Mono<T> limitCalls(){
        return Mono.deferContextual(ctx -> {
            Boolean allowCall = ctx.<String>getOrEmpty("category")
                    .map(RateLimiter::isAllowed)
                    .orElse(false);
            if(allowCall){
                return Mono.empty();
            }else{
                return Mono.error(new RuntimeException("Rate limit exceeded"));
            }
        });
    }

    private static boolean isAllowed(String category){
        var attempts = categoryAttempts.getOrDefault(category, 0);
        if(attempts > 0){
            categoryAttempts.put(category, attempts - 1);
            return true;
        }
        return false;
    }

    private static void refresh(){
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i -> {
                    log.info("Refreshing categoryAttempts");
                    categoryAttempts.clear();
                    categoryAttempts.put("standard", 2);
                    categoryAttempts.put("premium", 3);
                });
    }

}
