package org.udemy.rx.assignment.sec06.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec06.model.Order;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.HashMap;

public class InventoryService {
    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);
    private final HashMap<String, Integer> inventoryMap = new HashMap<>();

    public void publishInventory(Flux<Order> flux){
        flux
                .map(order -> {
            String key = order.category();
            inventoryMap.put(key, inventoryMap.getOrDefault(key, 500) - Integer.parseInt(order.quantity()));
            return inventoryMap;
        })
                .subscribeOn(Schedulers.newParallel("Inventory-Thread"))
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Inventory-Subscriber"));
    }

}
