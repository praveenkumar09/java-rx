package com.udemy.rx;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {
    private static final Logger log = LoggerFactory.getLogger(Lec07ScenarioNameTest.class);

    private Flux<Integer> getItems(){
        return Flux.range(1,3)
                .log();
    }

    @Test
    public void getItemsTestWithNextCount(){
        StepVerifierOptions options = StepVerifierOptions
                .create()
                .scenarioName("1 to 3 items test");
        StepVerifier
                .create(getItems(),options)
                .expectNext(1)
                .as("1st item expected to be 1")
                .expectNext(2,3)
                .as("Other items are expected to be 2 and 3")
                .expectComplete()
                .verify();
    }
}
