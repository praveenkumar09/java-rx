package org.udemy.rx.dev.sec03;

import org.slf4j.Logger;
import org.udemy.rx.common.Util;
import org.udemy.rx.dev.sec03.helper.NameGenerator;

public class Lec07fluxVsList {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Lec07fluxVsList.class);

    public static void main(String[] args) {
        //var list = NameGenerator.generateNamesList(10);
        //System.out.println(list);
        var flux = NameGenerator.generateNamesFlux(10);
        flux.subscribe(Util.subscriber("subscriber-1"));
    }

}
