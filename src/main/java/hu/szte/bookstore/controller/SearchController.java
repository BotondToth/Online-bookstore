package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.SearchDTO;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.service.SearchServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A keresessel kapcsolatos endpoint-ok kontrollere
 * @author Roland
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/search")
public class SearchController extends BaseController {


    private SearchServiceImp searchService;

    @Autowired
    public SearchController(SearchServiceImp searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/book/{title}/{author}/{genre}")
    public List<Book> getSerchBooks(@PathVariable final String title,@PathVariable final String author,@PathVariable final String genre) {
        return searchService.getBooksBySearch(title, author, genre);
    }

    @GetMapping("/{title}")
    public String getBooksByTitle(@PathVariable final String title) {
        return createGsonFromObject(searchService.getBooksByTitle(title));
    }

}
