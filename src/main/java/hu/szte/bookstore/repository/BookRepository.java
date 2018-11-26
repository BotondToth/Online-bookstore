package hu.szte.bookstore.repository;

import hu.szte.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author Botond
 */

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findAllByAuthor(String author);

    List<Book> findAllByReleaseDate(int releaseDate);

    List<Book> findAllByReleaseDateBetween(int from, int to);

    Book findByIsbn(String isbn);


    //@Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title) OR LOWER(b.author) = LOWER(:author) OR b.genre = (SELECT id FROM Genres where genre = :genre)")
    @Query(value = "SELECT * FROM Books WHERE LOWER(title) = LOWER(:title) OR LOWER(author) = LOWER(:author) OR genre = (SELECT id FROM Genres where genre = :genre)", nativeQuery = true)
    List<Book> findAllByTitleOrAuthorOrGenreIgnoreCase(@Param("title") String title, @Param("author") String author, @Param("genre") String genre);

    @Query("SELECT DISTINCT a.author FROM Book a")
    List<String> findAuthors();

    @Query("SELECT DISTINCT a.publisher FROM Book a")
    List<String> findPublishers();

}
