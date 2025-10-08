package org.udemy.rx.assignment.sec11.client;

import org.udemy.rx.assignment.sec06.model.Order;
import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getCountry() {
        return httpClient.get()
                .uri("/demo06/country")
                .responseContent()
                .asString();
    }

    public Flux<String> getProduct(int productId) {
        System.out.println(productId);
        return httpClient.get()
                .uri(String.format("/demo06/product/%d",productId))
                .responseContent()
                .asString();
    }
}
