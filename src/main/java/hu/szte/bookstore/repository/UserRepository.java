package hu.szte.bookstore.repository;

import hu.szte.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Botond
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

}
