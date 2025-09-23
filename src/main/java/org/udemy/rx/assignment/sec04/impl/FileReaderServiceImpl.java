package org.udemy.rx.assignment.sec04.impl;

import org.udemy.rx.assignment.sec04.service.FileReaderService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public Flux<String> read(Path path) {
        return Flux.generate(
                () -> openFile(path),
                this::readFile,
                this::closeFile
        );
    }

    private BufferedReader openFile(Path path){
        try {
            return Files.newBufferedReader(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BufferedReader readFile(BufferedReader bufferedReader, SynchronousSink<String> sink){
        try {
            String line = bufferedReader.readLine();
            if(line == null){
                bufferedReader.close();
                sink.complete();
            }else {
                sink.next(line);
            }
        } catch (IOException e) {
            sink.error(e);
        }
        return bufferedReader;
    }

    private void closeFile(BufferedReader bufferedReader){
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
