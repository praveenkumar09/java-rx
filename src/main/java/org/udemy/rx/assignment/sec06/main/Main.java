package org.udemy.rx.assignment.sec06.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec06.service.InventoryService;
import org.udemy.rx.assignment.sec06.service.RevenueService;
import org.udemy.rx.assignment.sec06.client.ExternalServiceClient;
import org.udemy.rx.assignment.sec06.model.Order;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final ExternalServiceClient externalServiceClient = new ExternalServiceClient();
    static RevenueService revenueService = new RevenueService();
    static InventoryService inventoryService = new InventoryService();

    public static void main(String[] args) {
        Flux<Order> fluxOrderAsMap = externalServiceClient.getOrders().replay().autoConnect(0);
        revenueService.publishRevenue(fluxOrderAsMap);
        inventoryService.publishInventory(fluxOrderAsMap);
        Util.sleep(20);
    }


}
