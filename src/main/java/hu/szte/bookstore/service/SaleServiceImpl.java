package hu.szte.bookstore.service;

import hu.szte.bookstore.model.Sale;
import hu.szte.bookstore.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Botond
 */
@Service
public class SaleServiceImpl {
    private final SalesRepository salesRepository;

    @Autowired
    public SaleServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> getSalesByDate(final Date date) {
        return salesRepository.findAllByPurchaseDate(date);
    }

    public List<Sale> getSalesByUserName(final String userName) {
        return salesRepository.findAllByUserName(userName);
    }

    public List<Sale> getSales() {
        return salesRepository.findAll();
    }


    public ResponseEntity createSale(final Sale sale) {
        try {
            salesRepository.save(sale);
            //create invoice here...
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
