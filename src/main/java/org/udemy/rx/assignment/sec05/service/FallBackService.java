package org.udemy.rx.assignment.sec05.service;

import org.udemy.rx.assignment.sec05.model.Product;
import reactor.core.publisher.Flux;

public interface FallBackService {
    Flux<Product> getProductFallback();
}
