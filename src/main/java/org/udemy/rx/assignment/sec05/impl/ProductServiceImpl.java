package org.udemy.rx.assignment.sec05.impl;

import org.udemy.rx.assignment.sec05.model.Product;
import org.udemy.rx.assignment.sec05.service.ProductService;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ProductServiceImpl implements ProductService {

    @Override
    public Flux<Product> getProducts() {
        return Flux
                .just(new Product(1),
                        new Product(2),
                        new Product(3),
                        new Product(4))
                .delayElements(Duration.ofSeconds(3));
    }
}
