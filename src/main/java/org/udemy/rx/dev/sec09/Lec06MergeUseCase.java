package org.udemy.rx.dev.sec09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec09.helper.Kayak;

public class Lec06MergeUseCase {

    private static final Logger log = LoggerFactory.getLogger(Lec06MergeUseCase.class);

    public static void main(String[] args) {
        log.info("Starting application");
        Kayak.getFlights()
                        .subscribe(Util.subscriber("subscriber-1"));
        Util.sleep(3);
    }

}
