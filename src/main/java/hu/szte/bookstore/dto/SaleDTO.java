package hu.szte.bookstore.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * @author Botond
 */
@Data
public class SaleDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "address")
    private String address;

    @Column(name = "purchased_item")
    private String purchasedItem;
}