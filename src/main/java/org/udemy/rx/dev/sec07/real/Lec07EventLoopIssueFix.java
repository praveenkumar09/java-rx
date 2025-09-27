package org.udemy.rx.dev.sec07.real;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec07.client.ExternalServiceClient;

public class Lec07EventLoopIssueFix {

    private static final Logger log = LoggerFactory.getLogger(Lec07EventLoopIssueFix.class);

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .map(Lec07EventLoopIssueFix::process)
                    .subscribe(Util.subscriber("subscriber-"+i));
        }

        Util.sleep(20);
    }

    private static String process(String input){
        Util.sleep(1);
        return input+"-processed";
    }
}
