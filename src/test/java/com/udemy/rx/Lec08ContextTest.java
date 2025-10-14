package com.udemy.rx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class Lec08ContextTest {
    private static final Logger log = LoggerFactory.getLogger(Lec08ContextTest.class);

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

    @Test
    public void testCountry(){
        StepVerifierOptions stepVerifierOptions =
                StepVerifierOptions
                .create()
                .withInitialContext(
                        Context
                                .of("country", "Brazil"));
        StepVerifier
                .create(getCountry(),stepVerifierOptions)
                .expectNext("Welcome Brazil")
                .expectComplete()
                .verify();

    }

    @Test
    public void testCountryError(){
        StepVerifierOptions stepVerifierOptions =
                StepVerifierOptions
                        .create()
                        .withInitialContext(
                                Context
                                        .of("user", "sam"));
        StepVerifier
                .create(getCountry(),stepVerifierOptions)
                .consumeErrorWith(throwable -> {
                    Assertions.assertEquals("Country not found", throwable.getMessage());
                    Assertions.assertEquals(RuntimeException.class, throwable.getClass());
                })
                .verify();

    }

}
