package org.udemy.rx.sec03.client;

import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<String> getProductName(int productId) {
        return httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next();
    }

    public Flux<String> getNameAsStream() {
        return httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<String> getStockPrice(){
        return httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString();
    }
}
