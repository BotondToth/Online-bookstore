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
        sale.setId(saleDTO.getId());
        sale.setUserName(saleDTO.getUserName());
        sale.setAddress(saleDTO.getAddress());
        sale.setPurchaseDate(saleDTO.getPurchaseDate());
        sale.setPurchasedItem(saleDTO.getPurchasedItem());
        return sale;
    }

    public static SaleDTO convertEntityToDTO(final Sale sale) {
        final SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setUserName(sale.getUserName());
        saleDTO.setAddress(sale.getAddress());
        saleDTO.setPurchaseDate(sale.getPurchaseDate());
        saleDTO.setPurchasedItem(sale.getPurchasedItem());
        return saleDTO;
    }


}
