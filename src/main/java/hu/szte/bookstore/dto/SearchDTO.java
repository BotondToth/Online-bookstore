package hu.szte.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roland
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDTO {

   private String title;

   private String author;

}
