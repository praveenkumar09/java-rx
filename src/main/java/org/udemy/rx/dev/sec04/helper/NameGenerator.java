package org.udemy.rx.dev.sec04.helper;

import org.udemy.rx.common.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = stringFluxSink;
    }

    public void generateCountryName(){
        this.fluxSink.next(Util.faker().country().name());
    }
}
