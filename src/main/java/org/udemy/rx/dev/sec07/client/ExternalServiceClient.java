package org.udemy.rx.dev.sec07.client;

import org.udemy.rx.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.scheduler.Schedulers;

public class ExternalServiceClient extends AbstractHttpClient {
    private static final Logger log = LoggerFactory.getLogger(ExternalServiceClient.class);


    public Flux<String> getProductName(int productId) {
        return httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .doOnNext(value -> log.info("Received product name: {}", value))
                .publishOn(Schedulers.boundedElastic());
    }
}
