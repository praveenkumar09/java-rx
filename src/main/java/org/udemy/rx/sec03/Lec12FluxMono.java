package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec12FluxMono {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec12FluxMono.class);

    public static void main(String[] args) {
        log.info("Lec12FluxMono main method");
        Mono<String> username = getUsername(1);
        save(Flux.from(username));
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }

    private static void save(Flux<String> flux){
        flux.subscribe(Util.subscriber("subscriber-1"));
    }
}
