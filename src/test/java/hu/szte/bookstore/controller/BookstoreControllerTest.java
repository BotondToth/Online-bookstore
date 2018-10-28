package hu.szte.bookstore.controller;

import hu.szte.bookstore.exception.BookNotFoundException;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.BookstoreServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 *
 * @author Botond
 */
@RunWith(MockitoJUnitRunner.class)
public class BookstoreControllerTest {

    @InjectMocks
    private BookstoreController tested;

    @Mock
    private BookstoreServiceImpl bookStoreService;

    @Before
    public void setup() throws BookNotFoundException {
        when(bookStoreService.getBookByIsbn("isbn")).thenReturn(new Book("isbn", "title", "author", 2018, "publisher", 1, 676, true));
        when(bookStoreService.getBookByIsbn("false")).thenReturn(null);
        when(bookStoreService.getAllBooks()).thenReturn(initMockedBooks());

        tested = new BookstoreController(bookStoreService);
    }

    /**
     * Testing get all books function
     */
    @Test
    public void test_getBooks_returnsValidData() {
        final List<Book> books = tested.getAllBooks();

        assertNotNull(books);
        assertEquals(4, books.size());
        assertEquals("isbn1", books.get(0).getIsbn());
        assertEquals("author2", books.get(1).getAuthor());
    }

    /**
     * Tries to find a book by isbn and succeeds
     */
    @Test
    public void test_getBookByIsbnGoodValue() throws BookNotFoundException {
        final Book book = tested.getBookByIsbn("isbn");

        assertNotNull(book);
        assertEquals("isbn", book.getIsbn());
    }

    /**
     * Tries to find a non-existing book by isbn, expects a null value
     */
    @Test
    public void test_getBookByIsbnBadValue() throws BookNotFoundException {
        final Book book = tested.getBookByIsbn("false");

        assertNull(book);
    }

    //------------------------
    // Private methods
    //------------------------

    private List<Book> initMockedBooks() {
        final List<Book> books = new ArrayList<>();
        books.add(new Book("isbn1", "title1", "author1", 2017, "publisher", 1, 676, true));
        books.add(new Book("isbn2", "title2", "author2", 2017, "publisher", 1, 676, true));
        books.add(new Book("isbn3", "title3", "author3", 2017, "publisher", 1, 676, true));
        books.add(new Book("isbn4", "title4", "author4", 2017, "publisher", 1, 676, true));
        return books;
    }

}
