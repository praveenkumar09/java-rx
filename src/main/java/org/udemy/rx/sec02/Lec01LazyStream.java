package org.udemy.rx.sec02;

import org.slf4j.Logger;

import java.util.stream.Stream;

public class Lec01LazyStream {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec01LazyStream.class);

    public static void main(String[] args) {
        Stream.of(1,2,3)
                .peek(i -> log.info("{}",i))
                .forEach(i -> log.info("{}",i));
    }


}
