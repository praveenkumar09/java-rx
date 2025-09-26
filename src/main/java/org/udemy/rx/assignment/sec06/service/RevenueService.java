package org.udemy.rx.assignment.sec06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec06.model.Order;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;

public class RevenueService {

    private static final Logger log = LoggerFactory.getLogger(RevenueService.class);
    private final HashMap<String, Integer> revenueMap = new HashMap<>();
    

    public void publishRevenue(Flux<Order> flux){
        flux
                .map(order -> {
            String key = order.category();
            if(revenueMap.containsKey(key)){
                revenueMap.put(key, revenueMap.get(key) + Integer.parseInt(order.price()));
            }else{
                revenueMap.put(key, Integer.parseInt(order.price()));
            }
            return revenueMap;
        })
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Revenue-Subscriber"));
    }
}
