package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.product.Product;

public class Lec08ZipAssignment {
    private static final Logger log = LoggerFactory.getLogger(Lec08ZipAssignment.class);

    public static void main(String[] args) {
        log.info("Starting application");
        for(int i = 0; i < 10; i++) {
            Product.generateProduct(i)
                    .subscribe(Util.subscriber("subscriber-"+i));
        }
        Util.sleep(3);
    }
}
