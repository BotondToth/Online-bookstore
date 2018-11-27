package hu.szte.bookstore.mapper;

import hu.szte.bookstore.dto.SaleDTO;
import hu.szte.bookstore.model.Sale;

/**
 *
 * @author Botond
 */
public class SaleEntityAndDTOMapper {


    public static Sale convertDTOToEntity(final SaleDTO saleDTO) {
        final Sale sale = new Sale();
        return sale;
    }

    public static SaleDTO convertEntityToDTO(final Sale sale) {
        final SaleDTO saleDTO = new SaleDTO();
        return saleDTO;
    }


}
