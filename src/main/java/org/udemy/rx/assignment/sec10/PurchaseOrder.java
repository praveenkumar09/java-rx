package org.udemy.rx.assignment.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Set;
import java.util.function.Function;

public record PurchaseOrder(String item, String category, Integer price){
    private static final Logger log = LoggerFactory.getLogger(PurchaseOrder.class);

    public PurchaseOrder setPrice(Integer price){
        return new PurchaseOrder(item(), category(), price);
    }


    private static final Set<String> allowedCat = Set.of("Kids","Automotive");

    public static Flux<PurchaseOrder> generatePurchaseOrder(){
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder(
                        Util.faker().commerce().productName(),
                        Util.faker().commerce().department(),
                        Util.faker().number().numberBetween(10,100)
                ))
                .filter(i -> allowedCat.contains(i.category()));
    }

    public static Flux<PurchaseOrder> processPurchaseOrder(GroupedFlux<String, PurchaseOrder> groupedFlux){
        return groupedFlux
                .flatMap(purchaseOrder -> processKidsOrAutomativeOrder(purchaseOrder,groupedFlux.key()));
    }

    private static Flux<PurchaseOrder> processKidsOrAutomativeOrder(PurchaseOrder purchaseOrder, String category) {
        switch (category){
            case "Kids" -> {
                return createKidsPurchaseOrderFlux(purchaseOrder);
            }
            case "Automotive" -> {
                return createAutomativePurchaseOrderFlux(purchaseOrder);
            }
        }
        return Flux.just(new PurchaseOrder("","",0));
    }

    private static Flux<PurchaseOrder> createAutomativePurchaseOrderFlux(PurchaseOrder purchaseOrder) {
        PurchaseOrder updatedPurchaseOrder = purchaseOrder.setPrice(purchaseOrder.price() + 100);
        log.info("Updated price for Automotive: {}", updatedPurchaseOrder);
        return Flux.just(updatedPurchaseOrder);
    }

    private static Flux<PurchaseOrder> createKidsPurchaseOrderFlux(PurchaseOrder purchaseOrder) {
        PurchaseOrder order =  new PurchaseOrder(purchaseOrder.item(), purchaseOrder.category(),0);
        log.info("Created new purchase order for Kids: {} from existing order : {}", order, purchaseOrder);
        return Flux.just(order)
                .startWith(purchaseOrder);
    }

}
