package org.udemy.rx.dev.sec09.product;

import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

public record Name(String name) {

    public static Flux<Name> getProductNames(int productId) {
        return new ExternalServiceClient()
                        .getDetails("/demo05/product/"+productId,
                                Name::new);
    }
}
