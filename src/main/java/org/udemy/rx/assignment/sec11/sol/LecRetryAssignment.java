package org.udemy.rx.assignment.sec11.sol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec11.client.ExternalServiceClient;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

public class LecRetryAssignment {
    private static final Logger log = LoggerFactory.getLogger(LecRetryAssignment.class);

    public static void main(String[] args) {
        log.info("Starting application");
        ExternalServiceClient serviceClient = new ExternalServiceClient();
        Flux.range(1,2)
                .flatMap(serviceClient::getProduct)
                .retry(2)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(10);
    }
}
