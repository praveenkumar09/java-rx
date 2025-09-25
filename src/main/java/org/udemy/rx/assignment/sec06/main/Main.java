package org.udemy.rx.assignment.sec06.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec06.client.ExternalServiceClient;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final ExternalServiceClient externalServiceClient = new ExternalServiceClient();
    public record Order(String item, String category, String price, String quantity){};
    public static HashMap<String, Integer> revenueMap = new HashMap<>();
    public static HashMap<String, Integer> inventoryMap = new HashMap<>();

    public static void main(String[] args) {
        Flux<Order> fluxOrderAsMap = externalServiceClient.getOrders().replay().autoConnect(0)
                .map(orderString -> {
                    String[] orderStringArray = orderString.split(":");
                    return new Order(orderStringArray[0], orderStringArray[1], orderStringArray[2], orderStringArray[3]);
                });
        fluxOrderAsMap
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

        fluxOrderAsMap
                .map(order -> {
                    String key = order.category();
                    inventoryMap.put(key, inventoryMap.getOrDefault(key, 500) - Integer.parseInt(order.quantity()));
                    return inventoryMap;
                })
                .delayElements(Duration.ofSeconds(2))
                .subscribe(Util.subscriber("Inventory-Subscriber"));
        Util.sleep(20);
    }
}
