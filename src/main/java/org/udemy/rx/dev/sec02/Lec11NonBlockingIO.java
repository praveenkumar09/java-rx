package org.udemy.rx.dev.sec02;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec02.client.ExternalServiceClient;

public class Lec11NonBlockingIO {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {
        log.info("main started");
        var client = new ExternalServiceClient();
        for(int i=0; i<5; i++){
        client.getProductName(i)
                .subscribe(Util.subscriber("subscriber-"+i));
        }
        Util.sleep(2);
        log.info("main finished");
    }
}
