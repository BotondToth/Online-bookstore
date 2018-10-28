package hu.szte.bookstore.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookNotFoundException extends Exception {

    public BookNotFoundException(final String msg) {
        super(msg);
        log.error(msg);
    }

}
