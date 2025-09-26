package org.udemy.rx.dev.sec07.sample;


import org.udemy.rx.common.Util;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.SynchronousSink;

import java.security.Provider;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameService implements Consumer<FluxSink<String>> {
    private static final Logger log = LoggerFactory.getLogger(NameService.class);

    private int hashcode;
    private FluxSink<String> fluxSink;

    @Override
    public void accept(FluxSink<String> val) {
        this.hashcode = val.hashCode();
        log.info("NameService {} received FluxSink",this.hashcode);
        this.fluxSink = val;
    }

    public void generateName(){
        log.info("NameService {} generated name",this.hashcode);
        this.fluxSink.next(Util.faker().name().firstName());
    }
}
