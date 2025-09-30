package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public record Product(Name name, Price price, Review review) {

    public static Flux<Product> generateProduct(int productId){
        return Flux.zip(Name.getProductNames(productId),
                        Review.getProductReview(productId),
                        Price.getProductPrice(productId))
                .transform(Util.fluxMapper(
                        t -> new Product(t.getT1(),
                                t.getT3(),
                                t.getT2())
                ));
    }
}
