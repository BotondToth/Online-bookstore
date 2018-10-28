package hu.szte.bookstore.service;

import hu.szte.bookstore.model.User;
import hu.szte.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Botond
 */
@Service
public class UserServiceImpl {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserName(final String userName) {
        return userRepository.findByUserName(userName);
    }

    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

}
