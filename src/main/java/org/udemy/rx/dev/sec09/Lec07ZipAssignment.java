package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.product.Name;
import org.udemy.rx.dev.sec09.product.Price;
import org.udemy.rx.dev.sec09.product.Product;
import org.udemy.rx.dev.sec09.product.Review;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07ZipAssignment {
    private static final Logger log = LoggerFactory.getLogger(Lec07ZipAssignment.class);

    public static void main(String[] args) {
        log.info("Starting application");
        for(int i = 0; i < 10; i++) {
            Product.generateProduct(i)
                    .subscribe(Util.subscriber("subscriber-"+i));
        }
        Util.sleep(3);
    }
}
