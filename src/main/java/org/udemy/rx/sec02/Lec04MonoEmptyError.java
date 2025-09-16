package org.udemy.rx.sec02;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(3)
                .subscribe(Util.subscriber("Lec04MonoEmptyError"));
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }
}
