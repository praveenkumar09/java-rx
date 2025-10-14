package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor(){
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    @Test
    public void testProcessor(){
        TestPublisher<String> testPublisher = TestPublisher
                .create();
        Flux<String> flux = testPublisher.flux();
        StepVerifier
                .create(flux.transform(processor()))
                .then(() -> testPublisher.emit(
                        "hai",
                        "hello"))
                .expectNext("HAI:3")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();
    }
}
