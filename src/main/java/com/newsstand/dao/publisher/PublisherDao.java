package com.newsstand.dao.publisher;

import com.newsstand.model.magazine.Publisher;

public interface PublisherDao {
    Publisher createPublisher(Publisher publisher);
    Publisher updatePublisher(Publisher publisher);
    void deleteById(Long id);
    Publisher findPublisherById(Long id);
}
