package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public record Name(String name) {

    public static Flux<Name> getProductNames() {
        return Flux
                .range(1,6)
                .transform(Util.fluxMapper(
                        i -> new Name(Util
                                .faker()
                                .company().name())
                ))
                .delayElements(Duration.ofMillis(100));
    }
}
