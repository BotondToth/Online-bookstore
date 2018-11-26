package hu.szte.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Botond
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDTO {

    private List<String> purchasedItems;
}