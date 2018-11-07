package hu.szte.bookstore.service;

import hu.szte.bookstore.dto.SearchDTO;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Roland
 *
 * Teszteli a SearchService getBooksBySearch() függvényét
 */

@Service
public class SearchServiceImp {

    private final BookRepository bookRepository;


    @Autowired
    public SearchServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooksBySearch(final SearchDTO searchDTO) {

        return bookRepository.findAllByTitleAndAuthor(searchDTO.getTitle(), searchDTO.getAuthor());
    }
}