package org.udemy.rx.dev.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Kayak {

    public static Flux<Flight> getFlights() {
        return Flux
                .merge(Emirates.getFlights(),
                        Qatar.getFlights(),
                        Malindo.getFlights())
                .take(Duration.ofSeconds(2));

    }
}
