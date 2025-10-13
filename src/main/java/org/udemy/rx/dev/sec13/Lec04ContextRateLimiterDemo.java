package org.udemy.rx.dev.sec13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec13.client.ExternalServiceClient;
import reactor.util.context.Context;

public class Lec04ContextRateLimiterDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec04ContextRateLimiterDemo.class);

    public static void main(String[] args) {
        log.info("Lec04ContextRateLimiterDemo started");
        ExternalServiceClient externalServiceClient = new ExternalServiceClient();
        for (int i = 0; i < 10; i++) {
            externalServiceClient.getBook("/demo07/book")
                    .contextWrite(Context.of("user", "mike"))
                    .subscribe(Util.subscriber("book-subscriber"));
        }
        Util.sleep(3);
    }
}
