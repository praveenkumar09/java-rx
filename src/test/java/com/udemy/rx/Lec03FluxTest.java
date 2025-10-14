package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {
    private static final Logger log = LoggerFactory.getLogger(Lec03FluxTest.class);

    private Flux<Integer> getItems(){
        return Flux.just(1,2,3,4,5)
                .log();
    }

    @Test
    public void getItemsTestWithOneItem(){
        StepVerifier
                .create(getItems(),1)
                .expectNext(1)
                .thenCancel()
                .verify();
    }

    @Test
    public void getItemsTestWithAllItems(){
        StepVerifier
                .create(getItems())
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }

}
