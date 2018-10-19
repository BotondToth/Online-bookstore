package hu.szte.bookstore.mapper;

import hu.szte.bookstore.dto.BookDTO;
import hu.szte.bookstore.model.Book;

/**
 *
 * @author Botond
 */
public class BookEntityAndDTOMapper {

    public static BookDTO mapEntityToDTO(final Book book) {
        final BookDTO dto = new BookDTO();
        //TODO: egyertelmu...
        return dto;
    }

    public static Book mapDTOToEntity(final BookDTO dto) {
        final Book book = new Book();
        //TODO: egyertelmu...
        return book;
    }
}
