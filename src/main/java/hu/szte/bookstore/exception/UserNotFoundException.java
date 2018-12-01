package hu.szte.bookstore.exception;

import hu.szte.bookstore.model.User;

/**
 * Nem talalhato felhasznalo eseten dobott kivetelosztaly
 * @author Botond
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException(final User user) {
        super(user.getEmail());
    }
}
