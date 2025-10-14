package com.udemy.rx;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.udemy.rx.common.Util;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

public class Lec05AssertNextTest {
    private static final Logger log = LoggerFactory
            .getLogger(Lec05AssertNextTest.class);

    record Book(int id, String author,String title){

    }

    private Flux<Book> getBooks(){
        com.github.javafaker.Book book = Util
                .faker()
                .book();
        return Flux.range(1,3)
                .map(i ->
                        new Book(i,
                                book.author(),
                                book.title()));
    }

    @Test
    public void getBooksTestWithAssertNext(){
        StepVerifier
                .create(getBooks())
                .assertNext(book ->{
                    Assertions.assertEquals(1,book.id());
                })
                .thenConsumeWhile(book -> Objects.nonNull(book.title()))
                .expectComplete()
                .verify();
    }

    @Test
    public void getBooksTestWithCollectAndTest(){
        StepVerifier
                .create(getBooks()
                .collectList())
                .assertNext(list -> {
                    Assertions.assertEquals(3,list.size());
                    Assertions.assertEquals(1,list.get(0).id());
                    Assertions.assertEquals(Book.class, list.get(0).getClass());
                })
                .expectComplete()
                .verify();
    }

}
