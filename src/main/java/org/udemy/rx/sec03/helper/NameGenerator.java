package org.udemy.rx.sec03.helper;

import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class NameGenerator {

    public static List<String> generateNamesList(int size) {
        return IntStream.range(1, size)
                .mapToObj(i -> nameGenerator())
                .toList();
    }

    public static Flux<String> generateNamesFlux(int size) {
        return Flux.range(1,size)
                .map(i -> nameGenerator());
    }



    private static String nameGenerator() {
        Util.sleep(1);
        return Util.faker().name().firstName();
    }
}
