package com.newsstand.service.publisher;

import com.newsstand.dao.publisher.MysqlPublisherDaoImpl;
import com.newsstand.dao.publisher.PublisherDao;
import com.newsstand.model.magazine.Publisher;
import org.apache.log4j.Logger;

import java.util.List;

public class PublisherServiceImpl implements PublisherService {

    private static final Logger LOGGER = Logger.getLogger(PublisherServiceImpl.class);

    private static PublisherServiceImpl INSTANCE;
    private static PublisherDao publisherDao;

    private PublisherServiceImpl() {
        LOGGER.info("Initializing PublisherServiceImpl");

        publisherDao = MysqlPublisherDaoImpl.getInstance();
    }

    public static PublisherServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PublisherServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Publisher> getAllPublishers() {
        LOGGER.info("Getting all publishers");
        return publisherDao.getAllPublishers();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        LOGGER.info("Getting publisher by id " + id);
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
        return publisherDao.deletePublisherById(id);
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        LOGGER.info("Updating publisher by id " + publisher.getId());
        return publisherDao.updatePublisher(publisher);
    }
}
