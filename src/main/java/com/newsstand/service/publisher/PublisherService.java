package com.newsstand.service.publisher;

import com.newsstand.model.magazine.Publisher;

import java.util.List;

public interface PublisherService {
    /**
     * This method gets all publishers.
     *
     * @return A list of all publishers.
     */
    List<Publisher> findAll();

    /**
     * This method gets publisher by id.
     *
     * @param id Id of the publisher to find.
     * @return   Publisher object.
     */
    Publisher findPublisherById(Long id);

    /**
     * This method creates new publisher.
     *
     * @param publisher Publisher object to be created.
     * @return          Updated object.
     */
    Publisher createPublisher(Publisher publisher);

    /**
     * This method deletes publisher.
     *
     * @param id Id of the publisher to delete.
     * @return   True if publisher deleted successfully, otherwise false.
     */
    boolean deletePublisherById(Long id);

    /**
     * This method updates publisher.
     *
     * @param publisher Object to be updated.
     * @return         An updated object.
     */
    Publisher updatePublisher(Publisher publisher);
}
