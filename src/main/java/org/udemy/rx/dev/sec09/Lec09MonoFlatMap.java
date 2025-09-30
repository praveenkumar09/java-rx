package org.udemy.rx.dev.sec09;

import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.applications.PaymentService;
import org.udemy.rx.dev.sec09.applications.UserService;

public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        UserService
                .getUserId("sam")
                .flatMap(PaymentService::getPayment)
                .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(4);
    }
}
