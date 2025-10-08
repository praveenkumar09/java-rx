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
        return Flux.interval(Duration.ofMillis(800))
                .transform(createPurchaseOrder())
                .filter(i -> allowedCat.contains(i.category()));
    }

    private static Function<Flux<Long>, Flux<PurchaseOrder>> createPurchaseOrder(){
        return flux -> flux
                .map(i -> new PurchaseOrder(
                        Util.faker().commerce().productName(),
                        Util.faker().commerce().department(),
                        Util.faker().number().numberBetween(10,100)
                ));
    }
    
    public static Mono<Void> processPurchaseOrder(GroupedFlux<String, PurchaseOrder> groupedFlux){
        return groupedFlux
                .doOnNext(i -> {
                    log.info("Processing purchase order: {}", i);
                    PurchaseOrder latestOrder = switch (i.category()){
                        case "Kids" -> {
                            PurchaseOrder order =  new PurchaseOrder(i.item(),i.category(),0);
                            log.info("Created new purchase order for Kids: {} from existing order : {}", order, i);
                            yield order;
                        }
                        case "Automotive" -> {
                            PurchaseOrder updatedPurchaseOrder = i.setPrice(i.price() + 100);
                            log.info("Updated price for Automotive: {}", updatedPurchaseOrder);
                            yield updatedPurchaseOrder;
                        }
                        default -> throw new RuntimeException("Invalid category");
                    };
                })
                .doOnComplete(() -> log.info("Completed processing purchase order for category : {}", groupedFlux.key()))
                .then();
    }
    
}
