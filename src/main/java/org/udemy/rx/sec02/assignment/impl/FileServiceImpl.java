package org.udemy.rx.sec02.assignment.impl;

import org.udemy.rx.sec02.assignment.service.FileService;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FileServiceImpl implements FileService {


    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromSupplier(() -> {
            try {
                return Files.readString(Path.of(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> {
            try {
                Files.writeString(Path.of(fileName), content);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Void> deleteFile(String fileName) {
        return Mono.fromRunnable(() -> {
            try {
                Files.delete(Path.of(fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
