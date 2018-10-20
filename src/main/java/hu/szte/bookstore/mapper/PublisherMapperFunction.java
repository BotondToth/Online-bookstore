package hu.szte.bookstore.mapper;

import hu.szte.bookstore.entities.Publisher;

import java.util.function.Function;

/**
 * {@link Publisher} osztaly elkeszitesehez mapper function
 * @author Botond
 */
public class PublisherMapperFunction implements Function<String, Publisher> {

    @Override
    public Publisher apply(final String name) {
        final Publisher publisher = new Publisher();
        publisher.setName(name);
        return publisher;
    }
}
