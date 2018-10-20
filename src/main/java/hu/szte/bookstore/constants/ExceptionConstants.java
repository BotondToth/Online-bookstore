package hu.szte.bookstore.constants;

import lombok.Data;

public enum ExceptionConstants {

    BOOK_NOT_FOUND("BOOK_NOT_FOUND"),
    USER_NOT_FOUND("USER_NOT_FOUND");

    final String message;

    ExceptionConstants(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
