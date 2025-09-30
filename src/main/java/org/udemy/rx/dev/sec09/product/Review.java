package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;


import java.time.Duration;

public record Review(String review) {
    public static Flux<Review> getProductReview(int productId){
        return new ExternalServiceClient()
                        .getDetails("/demo05/review/"+productId,
                                Review::new);
    }
}
