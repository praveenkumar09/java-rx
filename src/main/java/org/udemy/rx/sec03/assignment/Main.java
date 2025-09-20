package org.udemy.rx.sec03.assignment;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import org.udemy.rx.sec03.client.ExternalServiceClient;

public class Main {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Main method started");
        var externalService = new ExternalServiceClient();
        externalService.getStockPrice()
                .subscribe(new StockSubscriber());
        Util.sleep(21);
    }
}
