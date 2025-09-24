package org.udemy.rx.assignment.sec05.impl;

import org.udemy.rx.assignment.sec05.model.Product;
import org.udemy.rx.assignment.sec05.service.FallBackService;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class FallbackServiceImpl implements FallBackService {


    @Override
    public Flux<Product> getProductFallback() {
        return Flux
                .just(new Product(Util.faker().number().randomDigitNotZero()),
                        new Product(Util.faker().number().randomDigitNotZero()),
                        new Product(Util.faker().number().randomDigitNotZero()),
                        new Product(Util.faker().number().randomDigitNotZero()));
    }
}
