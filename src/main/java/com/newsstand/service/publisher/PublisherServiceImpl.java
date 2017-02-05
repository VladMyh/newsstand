package com.newsstand.service.publisher;

import com.newsstand.dao.publisher.MysqlPublisherDaoImpl;
import com.newsstand.dao.publisher.PublisherDao;
import com.newsstand.model.magazine.Publisher;
import org.apache.log4j.Logger;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = Logger.getLogger(PublisherServiceImpl.class);

    private PublisherDao publisherDao;

    public PublisherServiceImpl(PublisherDao publisherDao) {
        LOGGER.info("Initializing PublisherServiceImpl");

        this.publisherDao = publisherDao;
    }

    @Override
    public List<Publisher> findAll() {
        LOGGER.info("Getting all publishers");

        return publisherDao.findAll();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        LOGGER.info("Getting publisher by id " + id);

        if(id == null) {
            return null;
        }

        return publisherDao.findPublisherById(id);
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        LOGGER.info("Creating new publisher");

        if(publisherDao.findPublisherByTitle(publisher.getTitle()) == null) {
            return publisherDao.createPublisher(publisher);
        }

        return null;
    }

    @Override
    public boolean deletePublisherById(Long id) {
        LOGGER.info("Deleting publisher by id " + id);

        return id != null && publisherDao.deletePublisherById(id);

    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        LOGGER.info("Updating publisher by id " + publisher.getId());

        if(publisher == null) {
            return null;
        }

        return publisherDao.updatePublisher(publisher);
    }
}
