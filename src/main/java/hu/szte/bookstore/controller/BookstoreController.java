package hu.szte.bookstore.controller;

import com.google.gson.Gson;
import hu.szte.bookstore.entities.Author;
import hu.szte.bookstore.entities.Publisher;
import hu.szte.bookstore.exception.BookNotFoundException;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.BookstoreServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Botond
 */
@RestController
@RequestMapping("/books")
public class BookstoreController {

    private BookstoreServiceImpl bookstoreService;

    @Autowired
    public BookstoreController(BookstoreServiceImpl bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookstoreService.getAllBooks();
    }

    @GetMapping("/{title}")
    public String getBooksByTitle(@PathVariable final String title) {
        return createGsonFromObject(bookstoreService.getBooksByTitle(title));
    }

    @GetMapping("/{isbn}")
    public Book getBookByIsbn(@PathVariable final String isbn) throws BookNotFoundException {
        return bookstoreService.getBookByIsbn(isbn);
    }

    @GetMapping("/all/{from}/{to}")
    public List<Book> getBooksBetweenDate(@PathVariable final int from, @PathVariable final int to) {
        return bookstoreService.getBooksBetweenDate(from, to);
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return bookstoreService.getAllAuthors();
    }

    @GetMapping("/publishers")
    public List<Publisher> getPublishers() {
        return bookstoreService.getPublishers();
    }

    private String createGsonFromObject(List<Book> allBooks) {
        final Gson gson = new Gson();
        return gson.toJson(allBooks);
    }


}
