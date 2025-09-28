package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public record Product(Name name, Price price, Review review) {

    public static void generateProduct(){
        Flux.zip(Name.getProductNames(),
                        Review.getProductReview(),
                        Price.getProductPrice())
                .transform(Util.fluxMapper(
                        t -> new Product(t.getT1(),
                                t.getT3(),
                                t.getT2())
                ))
                .subscribe(Util.subscriber("subscriber-1"));
    }
}
