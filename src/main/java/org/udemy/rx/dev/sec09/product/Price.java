package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public record Price(int price) {
    public static Flux<Price> getProductPrice(){
        return Flux.range(1,7)
                .transform(Util.fluxMapper(
                        i -> new Price(Util
                                .faker()
                                .random()
                                .nextInt(1000,10000)
                        )
                ))
                .delayElements(Duration.ofMillis(200));
    }
}
