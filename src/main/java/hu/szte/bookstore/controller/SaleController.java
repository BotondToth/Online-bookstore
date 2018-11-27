package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.SaleDTO;
import hu.szte.bookstore.mapper.SaleEntityAndDTOMapper;
import hu.szte.bookstore.model.Book;
import hu.szte.bookstore.model.Sale;
import hu.szte.bookstore.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Botond
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sale")
public class SaleController extends BaseController {

    private final SaleServiceImpl saleService;

    private final List<String> basket = new ArrayList<>();

    @Autowired
    public SaleController(SaleServiceImpl saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @GetMapping("/all/{purchaseDate}")
    public List<Sale> getSalesByPurchaseDate(@PathVariable final String purchaseDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy dd MMMM", Locale.getDefault());
        Date date = format.parse(purchaseDate);
        return saleService.getSalesByDate(date);
    }

    @GetMapping("/{userName}")
    public List<Sale> getSalesByUserName(@PathVariable final String userName) {
        return saleService.getSalesByUserName(userName);
    }

    @GetMapping("/add/{username}")
    public String createSale(@PathVariable final String username) {
        if(!basket.isEmpty()){
            final ResponseEntity sale = saleService.createSale(basket, username);
            if (sale.equals( new ResponseEntity(HttpStatus.OK))) {
                return "Ok";
            }
            basket.clear();
        }
        return "Hiba";
    }

    @GetMapping("/isbn/{isbn}")
    public String addToBasket(@PathVariable final String isbn) {
        basket.add(isbn);
        return "Added";
    }

    @GetMapping("/getbooks")
    public String getBasketElements() {
        final List<Book> books = new ArrayList<>();
        basket.forEach(isbn -> {
            books.add(saleService.getBookByISBN(isbn));
        });
        return createGsonFromObject(books);
    }

    @GetMapping("/clear")
    public String clearBasket() {
        basket.clear();
        return "cleared";
    }



}