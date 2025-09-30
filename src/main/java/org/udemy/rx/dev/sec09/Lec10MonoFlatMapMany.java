package org.udemy.rx.dev.sec09;


import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.applications.OrderService;
import org.udemy.rx.dev.sec09.applications.UserService;
import reactor.core.publisher.Mono;

public class Lec10MonoFlatMapMany {
    public static void main(String[] args) {
        UserService.getUserId("sam")
                .flatMapMany(OrderService::getOrders)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(4);
    }
}
