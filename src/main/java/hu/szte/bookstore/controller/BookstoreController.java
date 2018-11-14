package hu.szte.bookstore.controller;

import hu.szte.bookstore.exception.BookNotFoundException;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.BookstoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Botond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/books")
public class BookstoreController extends BaseController {

    private BookstoreServiceImpl bookstoreService;

    @Autowired
    public BookstoreController(BookstoreServiceImpl bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookstoreService.getAllBooks();
    }

    @GetMapping("/isbn/{isbn}")
    public String getBookByIsbn(@PathVariable final String isbn) throws BookNotFoundException {
        return createGsonFromObject(bookstoreService.getBookByIsbn(isbn));
    }

    @GetMapping("/all/{from}/{to}")
    public String getBooksBetweenDate(@PathVariable final int from, @PathVariable final int to) {
        return createGsonFromObject(bookstoreService.getBooksBetweenDate(from, to));
    }

    @GetMapping("/authors")
    public String getAllAuthors() {
        return createGsonFromObject(bookstoreService.getAllAuthors());
    }

    @GetMapping("/publishers")
    public String getPublishers() {
        return createGsonFromObject(bookstoreService.getPublishers());
    }


}
