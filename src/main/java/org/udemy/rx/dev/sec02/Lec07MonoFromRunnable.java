package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec07MonoFromRunnable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec07MonoFromRunnable.class);

    public static void main(String[] args) {
        List<Integer> productIds = List.of(1,2,3,4,5);
        productIds.forEach(id -> getProductName(id).subscribe(Util.subscriber("subscriber-"+id)));
    }

    private static Mono<String> getProductName(int productId){
        if(productId == 1){
            return Mono.just(Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(()->notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        log.info("notifying business regarding product id: {}",productId);
    }
}
