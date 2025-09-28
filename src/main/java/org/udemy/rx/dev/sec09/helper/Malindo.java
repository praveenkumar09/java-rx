package org.udemy.rx.dev.sec09.helper;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Malindo {

    private static final String AIRLINE = "Malindo";

    public static Flux<Flight> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(5,10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200,1200)))
                .transform(Util.
                        fluxMapper(i -> new Flight(AIRLINE,Util.faker().random().nextInt(300,1200))))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
