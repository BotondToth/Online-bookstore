package hu.szte.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Botond
 */
@Entity
@Table(name = "SALES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sale {

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
