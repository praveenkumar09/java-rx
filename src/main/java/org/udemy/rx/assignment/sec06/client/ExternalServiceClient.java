package org.udemy.rx.assignment.sec06.client;

import org.udemy.rx.assignment.sec06.main.Main;
import org.udemy.rx.assignment.sec06.model.Order;
import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<Order> getOrders() {
        return httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString()
                .map(mapStringToOrderFunction());
    }

    private Function<String,Order> mapStringToOrderFunction() {
        return orderString -> {
            String[] orderStringArray = orderString.split(":");
            return new Order(orderStringArray[0], orderStringArray[1], orderStringArray[2], orderStringArray[3]);
        };
    }
}
