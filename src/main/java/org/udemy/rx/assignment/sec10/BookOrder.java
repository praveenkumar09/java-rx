package org.udemy.rx.assignment.sec10;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public record BookOrder(String genre, String title, Integer price) {

    public static Flux<BookOrder> generateBookOrder(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> {
                    var genre = Util.faker().book().genre();
                    var title = Util.faker().book().title();
                    var price = Util.faker().number().numberBetween(10,100);
                    return new BookOrder(genre,title,price);
                });
    }

}
