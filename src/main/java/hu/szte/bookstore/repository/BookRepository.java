package hu.szte.bookstore.repository;

import hu.szte.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Botond
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByTitle(String title);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByTitleAndAuthor(String title, String author);

    List<Book> findAllByReleaseDate(int releaseDate);

    List<Book> findAllByReleaseDateBetween(int from, int to);

    Book findByIsbn(String isbn);

    @Query("SELECT DISTINCT a.author FROM Book a")
    List<String> findAuthors();

    @Query("SELECT DISTINCT a.publisher FROM Book a")
    List<String> findPublishers();

}
