package org.udemy.rx.dev.sec09.applications;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static final Map<Integer, List<Order>> orders = Map.of(
            1, List.of(
                    new Order(1,
                            Util.faker().commerce().productName(),
                            Util.faker().commerce().price()),
                    new Order(1,
                            Util.faker().commerce().productName(),
                            Util.faker().commerce().price())
            ),
            2, List.of(
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().commerce().price()),
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().commerce().price()),
                    new Order(2,
                            Util.faker().commerce().productName(),
                            Util.faker().commerce().price())
            ),
            3, List.of()
    );

    public Flux<Order> getOrders(int userId){
        return Flux.fromIterable(orders.get(userId))
                .delayElements(Duration.ofMillis(500));
    }

}
