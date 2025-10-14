package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {
    private static final Logger log = LoggerFactory.getLogger(Lec04RangeTest.class);

    private Flux<Integer> getItems(){
        return Flux.range(1,10)
                .log();
    }

    private Flux<Integer> getRandomNumbers(){
        return Flux.range(1,10)
                .map(i -> Util.faker().random().nextInt(1,10))
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

    @Test
    public void getItemsTestWithRandomNumbers(){
        StepVerifier
                .create(getRandomNumbers())
                .expectNextMatches(i -> i >= 1 && i <= 10)
                .expectNextCount(9)
                .expectComplete()
                .verify();
    }

    @Test
    public void getItemsTestWithRandomNumbers_ThenConsumeWhile(){
        StepVerifier
                .create(getRandomNumbers())
                .thenConsumeWhile(i -> i <= 10)
                .expectComplete()
                .verify();
    }

}
