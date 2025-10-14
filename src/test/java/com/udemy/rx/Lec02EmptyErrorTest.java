package com.udemy.rx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {
    private static final Logger log = LoggerFactory.getLogger(Lec02EmptyErrorTest.class);

    private static Mono<String> getUsername(int userId){
        return switch (userId) {
            case 1 -> Mono.just("John");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }

    @Test
    public void testUsernameJohn(){
        StepVerifier.create(getUsername(1))
                .expectNext("John")
                .expectComplete()
                .verify();
    }

    @Test
    public void testUsernameEmpty(){
        StepVerifier
                .create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void testUsernameErrorByClass(){
        StepVerifier
                .create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void testUsernameErrorByEmptyError(){
        StepVerifier
                .create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    public void testUsernameErrorByErrorMessage(){
        StepVerifier
                .create(getUsername(3))
                .expectErrorMessage("User not found")
                .verify();
    }

    @Test
    public void testUsernameErrorByErrorClassAndErrorMessage(){
        StepVerifier
                .create(getUsername(3))
                .consumeErrorWith(throwable -> {
                    Assertions.assertEquals("User not found", throwable.getMessage());
                    Assertions.assertEquals(RuntimeException.class, throwable.getClass());
                })
                .verify();
    }

}
