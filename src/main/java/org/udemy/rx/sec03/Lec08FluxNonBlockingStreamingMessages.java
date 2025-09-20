package org.udemy.rx.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import org.udemy.rx.sec03.client.ExternalServiceClient;

public class Lec08FluxNonBlockingStreamingMessages {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec08FluxNonBlockingStreamingMessages.class);

    public static void main(String[] args) {
        var externalServiceClient = new ExternalServiceClient();
        externalServiceClient.getNameAsStream()
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(6);
    }
}
