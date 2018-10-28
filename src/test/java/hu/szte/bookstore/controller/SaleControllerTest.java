package hu.szte.bookstore.controller;

import hu.szte.bookstore.model.Sale;
import hu.szte.bookstore.service.SaleServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 *
 * @author Botond
 */
@RunWith(MockitoJUnitRunner.class)
public class SaleControllerTest {

    @InjectMocks
    private SaleController tested;

    @Mock
    private SaleServiceImpl saleService;


    @Before
    public void setup() {
        final List<Sale> allSales = createAllSales();
        when(saleService.getSales()).thenReturn(allSales);
        when(saleService.getSalesByUserName("user1")).thenReturn(allSales.stream()
                .filter(p -> "user1".equals(p.getUserName()))
                .collect(Collectors.toList()));

        tested = new SaleController(saleService);
    }

    @Test
    public void test_getAllSales() {
        final List<Sale> all = tested.getSales();

        assertNotNull(all);
        assertEquals(all.size(), 4);
        assertEquals("user1", all.get(0).getUserName());
    }

    @Test
    public void test_getSalesByUserName() {
        final List<Sale> salesByUsername = tested.getSalesByUserName("user1");

        assertNotNull(salesByUsername);
        assertEquals(salesByUsername.size(), 3);
    }

    @Test
    @Ignore
    public void test_getSalesByDate() throws ParseException {
        final List<Sale> salesByDate = tested.getSalesByPurchaseDate(new Date().toString());

        assertNotNull(salesByDate);
    }


    //------------------------
    // Private methods
    //------------------------


    private List<Sale> createAllSales() {
        final List<Sale> sales = new ArrayList<>();
        sales.add(new Sale( 1L, "user1", new Date(), "address", "isbn1"));
        sales.add(new Sale( 2L, "user1", new Date(), "address", "isbn2"));
        sales.add(new Sale( 3L, "user2", new Date(), "address2", "isbn1"));
        sales.add(new Sale( 4L, "user1", new Date(), "address", "isbn3"));
        return sales;
    }

}