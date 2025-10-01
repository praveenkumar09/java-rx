package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.applications.OrderService;
import reactor.core.publisher.Flux;

public class Lec14CollectList {
    private static final Logger log = LoggerFactory.getLogger(Lec14CollectList.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Flux.range(1,10)
                .flatMap(i -> OrderService.getOrders(1))
                .collectList()
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(10);
    }
}
