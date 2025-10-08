package org.udemy.rx.assignment.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;

public class OrderMain {
    private static final Logger log = LoggerFactory.getLogger(OrderMain.class);

    public static void main(String[] args) {
        log.info("Starting application");
        PurchaseOrder.generatePurchaseOrder()
                .groupBy(PurchaseOrder::category)
                        .flatMap(PurchaseOrder::processPurchaseOrder)
                                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(60);
    }
}
