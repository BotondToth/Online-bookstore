package hu.szte.bookstore.constants;

import lombok.Getter;

public enum ExceptionConstants {

    BOOK_NOT_FOUND("BOOK_NOT_FOUND"),
    USER_NOT_FOUND("USER_NOT_FOUND");

    @Getter
    final String message;

    ExceptionConstants(final String message) {
        this.message = message;
    }

}
