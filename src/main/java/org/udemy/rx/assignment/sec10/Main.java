package org.udemy.rx.assignment.sec10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final List<String> interestedList = List.of("Science fiction", "Fantasy","Suspense/Thriller");
    private static final Map<String, Integer> interestedRevenueMap = new HashMap<>();

    public static void main(String[] args) {
        BookOrder.generateBookOrder()
                .filter(i -> interestedList.contains(i.genre()))
                .buffer(Duration.ofSeconds(5))
                .map(i -> {
                    i.forEach(j -> interestedRevenueMap.merge(j.genre(), j.price(), Integer::sum));
                    return interestedRevenueMap;
                })
                .subscribe(Util.subscriber("subscriber"));

        Util.sleep(60);
    }
}
