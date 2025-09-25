package org.udemy.rx.assignment.sec06.client;

import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getOrders() {
        return httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString();
    }
}
