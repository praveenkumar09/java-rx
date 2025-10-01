package org.udemy.rx.assignment.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {

    private static final Map<Integer,Integer> paymentTable = Map. of(
            1,100,
            2,200,
            3,300
    );

    public static Mono<Integer> getPayment(int userId){
        return Mono.justOrEmpty(paymentTable.get(userId));
    }
}
