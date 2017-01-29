package com.newsstand.dao.publisher;

import com.newsstand.model.magazine.Publisher;

import java.util.List;

public interface PublisherDao {

    /**
     * This method saves new publisher to db.
     *
     * @param publisher Object to be created.
     * @return          An updated object.
     */
    Publisher createPublisher(Publisher publisher);

    /**
     * This method updates publisher.
     *
     * @param publisher Object to be updated.
     * @return          An updated object.
     */
    Publisher updatePublisher(Publisher publisher);

    /**
     * This method deletes publisher from db.
     *
     * @param id Id of the publisher to be deleted.
     * @return   True if deletion successful, otherwise false.
     */
    boolean deletePublisherById(Long id);

    /**
     * This method finds publisher by id.
     *
     * @param id Id of the publisher.
     * @return   Publisher object retrieved from db, otherwise null.
     */
    Publisher findPublisherById(Long id);

    /**
     * This method gets all publishers
     *
     * @return A list of all publishers.
     */
    List<Publisher> findAll();

    /**
     * This method finds publisher by title.
     *
     * @param title Title of the publisher.
     * @return     Publisher object with given title, otherwise null.
     */
    Publisher findPublisherByTitle(String title);
}
