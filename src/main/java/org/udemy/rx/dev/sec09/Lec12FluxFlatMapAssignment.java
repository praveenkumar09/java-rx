package org.udemy.rx.dev.sec09;

import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.product.Product;
import reactor.core.publisher.Flux;

public class Lec12FluxFlatMapAssignment {
    public static void main(String[] args) {
        System.out.println("Lec12FluxFlatMapAssignment main method");
        Flux.range(1, 10)
                .flatMap(Product::generateProduct)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(10);
    }
}
