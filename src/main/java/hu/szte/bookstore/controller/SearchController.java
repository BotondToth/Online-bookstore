package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.SearchDTO;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.SearchServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Roland
 * Keresni lehet szerzőre és címre
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/search")
public class SearchController {


    private SearchServiceImp searchService;

    @Autowired
    public SearchController(SearchServiceImp searchService) {
        this.searchService = searchService;
    }
    @GetMapping("/book")
    public List<Book> getSerchBooks(@RequestParam final SearchDTO searchDTO) {
        return searchService.getBooksBySearch(searchDTO);
    }



}
