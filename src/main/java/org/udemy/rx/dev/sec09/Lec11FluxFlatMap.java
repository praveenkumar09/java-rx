package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.applications.OrderService;
import org.udemy.rx.dev.sec09.applications.UserService;

public class Lec11FluxFlatMap {
    private static final Logger log = LoggerFactory.getLogger(Lec11FluxFlatMap.class);

    public static void main(String[] args) {
        UserService.getUsers()
                .flatMap(user -> OrderService
                        .getOrders(user.id()),1)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(10);
    }
}
