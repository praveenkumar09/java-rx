package org.udemy.rx.assignment.sec11.sol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec11.client.ExternalServiceClient;
import org.udemy.rx.common.Util;

public class LecRepeatAssignment {
    private static final Logger log = LoggerFactory.getLogger(LecRepeatAssignment.class);

    public static void main(String[] args) {
        log.info("Starting application");
        ExternalServiceClient client = new ExternalServiceClient();
        client.getCountry()
                .repeat(3)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }
}
