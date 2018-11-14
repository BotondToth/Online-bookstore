package hu.szte.bookstore.service;

import hu.szte.bookstore.constants.ExceptionConstants;
import hu.szte.bookstore.entities.Author;
import hu.szte.bookstore.entities.Publisher;
import hu.szte.bookstore.exception.BookNotFoundException;
import hu.szte.bookstore.mapper.AuthorMapperFunction;
import hu.szte.bookstore.mapper.PublisherMapperFunction;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Botond
 */
@Service
public class BookstoreServiceImpl {

    private final BookRepository bookRepository;

    private final AuthorMapperFunction authorMapper;

    private final PublisherMapperFunction publisherMapper;

    @Autowired
    public BookstoreServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.authorMapper = new AuthorMapperFunction();
        publisherMapper = new PublisherMapperFunction();
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(final String isbn) throws BookNotFoundException {
        final Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            throw new BookNotFoundException(ExceptionConstants.BOOK_NOT_FOUND.getMessage());
        }
        return book;
    }

    public List<Book> getBooksByAuthor(final String author) {
        return bookRepository.findAllByAuthor(author);
    }

    public List<Book> getBooksByReleaseDate(final int releaseDate) {
        return bookRepository.findAllByReleaseDate(releaseDate);
    }

    public List<Book> getBooksBetweenDate(int from, int to) {
        return bookRepository.findAllByReleaseDateBetween(from, to);
    }

    public List<Author> getAllAuthors() {
        return bookRepository.findAuthors().stream()
                .map(authorMapper)
                .collect(Collectors.toList());
    }

    public List<Publisher> getPublishers() {
        return bookRepository.findPublishers().stream()
                .map(publisherMapper)
                .collect(Collectors.toList());
    }


    // -----------------------------
    // Private methods
    // -----------------------------
}
