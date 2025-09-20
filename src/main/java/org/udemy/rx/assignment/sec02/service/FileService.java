package org.udemy.rx.assignment.sec02.service;

import reactor.core.publisher.Mono;

public interface FileService {

    Mono<String> read(String fileName);
    Mono<Void>  write(String fileName, String content);
    Mono<Void> deleteFile(String fileName);
}
