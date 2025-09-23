package org.udemy.rx.dev.sec05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {
    private static final Logger log = LoggerFactory.getLogger(Lec10Transform.class);

    record Customer(int id, String name){}
    record Order(String productName, int quantity, int price){}


    public static void main(String[] args) {
        log.info("Application started");

        getCustomers()
                .transform(addCommonOperators())
                .subscribe();

        getOrders()
                .transform(addCommonOperators())
                .subscribe();
    }

    private static Flux<Customer> getCustomers(){
        return Flux.range(1,3)
                .map(i -> new Customer(i, "Customer-" + i));
    }

    private static Flux<Order> getOrders(){
        return Flux.range(1,3)
                .map(i -> new Order("Product-" + i, i, i*10));
    }

    private static <T> Function<Flux<T>, Flux<T>> addCommonOperators(){
        return flux -> flux
                .doOnNext(i -> log.info("received : {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("Error occurred", err));
    }
}
