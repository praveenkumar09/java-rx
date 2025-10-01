package org.udemy.rx.assignment.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.assignment.sec09.applications.OrderService;
import org.udemy.rx.assignment.sec09.applications.UserInformation;
import org.udemy.rx.common.Util;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Main method started");
        UserInformation.getAllUserInformation()
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(20);

    }
}
