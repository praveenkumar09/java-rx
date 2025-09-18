package org.udemy.rx.sec02.assignment.service;

import reactor.core.publisher.Mono;

public interface FileService {

    Mono<String> read(String fileName);
    Mono<Void>  write(String fileName, String content);
    Mono<Void> deleteFile(String fileName);
}
