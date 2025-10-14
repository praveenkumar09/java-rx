package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {
    private static final Logger log = LoggerFactory.getLogger(Lec06VirtualTimeTest.class);

    private Flux<Integer> getItems(){
        return Flux.range(1,5)
                .delayElements(Duration.ofSeconds(10))
                .log();
    }

    @Test
    public void _testGetItems(){
        StepVerifier
                .create(getItems())
                .expectNext(1,2,3,4,5)
                .expectNextCount(5)
                .expectComplete()
                .verify();
    }

    @Test
    public void _testGetItemsWithVirtualTimer(){
        StepVerifier
                .withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1,2)
                .expectNextCount(3)
                .expectComplete()
                .verify();
    }

    @Test
    public void _testGetItemsWithExpectNoEvent(){
        StepVerifier
                .withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2,3,4,5)
                .expectComplete()
                .verify();
    }


}
