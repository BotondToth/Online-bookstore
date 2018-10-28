package hu.szte.bookstore.repository;

import hu.szte.bookstore.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Botond
 */
@Repository
public interface SalesRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByPurchaseDate(Date date);

    List<Sale> findAllByUserName(String userName);

}
