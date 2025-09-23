package org.udemy.rx.assignment.sec04.service;

import reactor.core.publisher.Flux;

import java.nio.file.Path;

public interface FileReaderService {
    Flux<String> read(Path path);
}
