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
                .transform(transformToUserInfo(id,username));
    }

    private static Function<Flux<Tuple2<Integer,List<Order>>>,Flux<UserInformation>> transformToUserInfo(Integer id, String username) {
        return flux -> flux
                .map(val -> new UserInformation(id,username,val.getT1(),val.getT2()));
    }
}
