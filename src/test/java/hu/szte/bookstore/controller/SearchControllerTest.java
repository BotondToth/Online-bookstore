package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.SearchDTO;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.SearchServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Roland
 */

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {

    @InjectMocks
    private SearchController searchController;

    @Mock
    private SearchServiceImp searchService;

    @Before
    public void setup() {
        when(searchService.getBooksBySearch(createDTO())).thenReturn(createSearchBook());

        searchController = new SearchController(searchService);

    }
    @Test
    public void testSerchBook(){

        final List<Book> books = searchController.getSerchBooks(createDTO());
        assertEquals(books.size(), 4);
    }

    private List<Book> createSearchBook() {

        final List<Book> books = new ArrayList<>();
        books.add(new Book("isbn1", "title1", "author1", 2017, "publisher", 1, 676, true, 1000, null));
        books.add(new Book("isbn2", "title2", "author2", 2017, "publisher", 1, 676, true, 1000, null));
        books.add(new Book("isbn3", "title3", "author3", 2017, "publisher", 1, 676, true,1000, null));
        books.add(new Book("isbn4", "title4", "author4", 2017, "publisher", 1, 676, true,1000, null));
        return books;
    }

    private SearchDTO createDTO() {

        SearchDTO searchDto = new SearchDTO();
        searchDto.setAuthor("author");
        searchDto.setTitle("title");
        return  searchDto;
    }

}
