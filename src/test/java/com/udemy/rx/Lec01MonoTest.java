package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec01MonoTest {
    private static final Logger log = LoggerFactory.getLogger(Lec01MonoTest.class);

    private Mono<String> getProduct(int productId){
        return Mono
                .fromSupplier(() -> "Product-" + productId)
                .doFirst(()-> log.info("Getting product Invoked : {}", productId));
    }

    @Test
    public void testMono(){
        StepVerifier.create(getProduct(1))
                .expectNext("Product-1")
                .expectComplete()
                .verify();
    }

}
