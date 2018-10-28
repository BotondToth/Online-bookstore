package hu.szte.bookstore.mapper;

import hu.szte.bookstore.entities.Author;

import java.util.function.Function;

/**
 * {@link Author} osztaly elkeszitesehez mapper function
 * @author Botond
 */
public class AuthorMapperFunction implements Function<String, Author> {

    @Override
    public Author apply(final String authorName) {
        final Author author = new Author();
        author.setName(authorName);
        return author;
    }
}
