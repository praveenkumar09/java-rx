package org.udemy.rx.sec02;

import org.reactivestreams.Subscription;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(1)
                .subscribe(System.out::println,
                        err -> System.out.println(err.getMessage()),
                        () -> System.out.println("Completed"),
                        subscription -> {
                            System.out.println("Default subscription triggered");
                            subscription.request(1);
                        });
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }
}
