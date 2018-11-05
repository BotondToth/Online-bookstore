package hu.szte.bookstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Botond
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {

    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "RELEASE_DATE")
    private int releaseDate;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "GENRE")
    private int genre;

    @Column(name = "PAGES")
    private int pages;

    @Column(name = "IS_NEW")
    private boolean isNew;

    @Column(name = "PRICE")
    private boolean price;

}
