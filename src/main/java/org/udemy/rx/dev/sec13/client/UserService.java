package org.udemy.rx.dev.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

    private static final Map<String,String> USER_CATEGORY = Map.of(
            "sam","standard",
            "mike","premium"
    );

    static Function<Context, Context> getUserCategoryContext(){
        return ctx -> ctx.<String>getOrEmpty("user")
                .filter(USER_CATEGORY::containsKey)
                .map(USER_CATEGORY::get)
                .map(i -> ctx.put("category",i))
                .orElse(Context.empty());
    }
}
