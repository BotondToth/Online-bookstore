package hu.szte.bookstore.controller;

import hu.szte.bookstore.dto.SaleDTO;
import hu.szte.bookstore.mapper.SaleEntityAndDTOMapper;
import hu.szte.bookstore.model.Sale;
import hu.szte.bookstore.service.SaleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @author Botond
 */
@RestController
@RequestMapping("/sale")
public class SaleController {

    private final SaleServiceImpl saleService;

    @Autowired
    public SaleController(SaleServiceImpl saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/all")
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @GetMapping("/all/{purchaseDate}")
    public List<Sale> getSalesByPurchaseDate(final String purchaseDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy dd MMMM", Locale.getDefault());
        Date date = format.parse(purchaseDate);
        return saleService.getSalesByDate(date);
    }

    @GetMapping("/{userName}")
    public List<Sale> getSalesByUserName(final String userName) {
        return saleService.getSalesByUserName(userName);
    }

    @PostMapping
    public ResponseEntity createSale(@PathVariable final SaleDTO saleDTO) {
        return saleService.createSale(SaleEntityAndDTOMapper.convertDTOToEntity(saleDTO));
    }


}