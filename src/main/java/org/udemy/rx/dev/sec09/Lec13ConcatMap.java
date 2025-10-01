package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.product.Product;
import reactor.core.publisher.Flux;

public class Lec13ConcatMap {
    private static final Logger log = LoggerFactory.getLogger(Lec13ConcatMap.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Flux.range(1, 10)
                .concatMap(Product::generateProduct,10)
                .subscribe(Util.subscriber("subscriber-1"))
        ;
    }
}
