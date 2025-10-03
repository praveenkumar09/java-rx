package org.udemy.rx.assignment.sec09.applications;
import org.reactivestreams.Publisher;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.List;
import java.util.function.Function;

public record UserInformation(Integer userId,
                              String username,
                              Integer balance,
                              List<Order> orders) {

    public static Flux<UserInformation> getAllUserInformation(){
        return UserService
                .getUsers()
                .flatMap(user -> getUserInformation(user.id(),user.username()));
    }

    private static Flux<UserInformation> getUserInformation(Integer id, String username) {
        return Flux.zip(
                PaymentService.getPayment(id),
                OrderService.getOrders(id).collectList()
        )
                .map(t -> new UserInformation(id,username,t.getT1(),t.getT2()));
    }

}
