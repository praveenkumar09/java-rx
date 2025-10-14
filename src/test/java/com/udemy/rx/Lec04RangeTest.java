package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {
    private static final Logger log = LoggerFactory.getLogger(Lec04RangeTest.class);

    private Flux<Integer> getItems(){
        return Flux.range(1,10)
                .log();
    }

    @Test
    public void getItemsTestWithNextCount(){
        StepVerifier
                .create(getItems())
                .expectNext(1,2,3)
                .expectNextCount(7)
                .expectComplete()
                .verify();
    }

}
