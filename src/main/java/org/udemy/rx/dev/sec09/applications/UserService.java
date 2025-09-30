package org.udemy.rx.dev.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    private static final Map<String,Integer>  userTable = Map. of(
      "san",1,
      "mike",2,
      "jane",3
    );

    public static Flux<User> getUsers(){
        return Flux.fromIterable(userTable.entrySet())
                .map(es -> new User(es.getValue(), es.getKey()));
    }

    public static Mono<Integer> getUserId(String userName){
        return Mono.justOrEmpty(userTable.get(userName));
    }


}
