package org.udemy.rx.assignment.sec05;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec05.impl.FallbackServiceImpl;
import org.udemy.rx.assignment.sec05.impl.ProductServiceImpl;
import org.udemy.rx.assignment.sec05.model.Product;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ProductServiceImpl productService = new ProductServiceImpl();

        productService.getProducts()
                .transform(commonOpForProducts())
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

    private static Function<Flux<Product>, Flux<Product>> commonOpForProducts(){
        FallbackServiceImpl fallbackService = new FallbackServiceImpl();
        return flux -> flux
                .timeout(Duration.ofSeconds(2),
                        fallbackService.getProductFallback())
                .switchIfEmpty(fallbackService.getProductFallback());
    }
}
