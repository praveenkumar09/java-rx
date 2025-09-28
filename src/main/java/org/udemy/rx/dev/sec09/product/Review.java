package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public record Review(int review) {
    public static Flux<Review> getProductReview(){
        return Flux.range(1,5)
                .transform(Util.fluxMapper(
                        i -> new Review(Util
                                .faker()
                                .random()
                                .nextInt(1,5))
                ))
                .delayElements(Duration.ofMillis(300));
    }
}
