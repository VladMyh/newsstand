package com.newsstand.dao.publisher;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.model.magazine.Publisher;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class MysqlPublisherDaoImpl implements PublisherDao {
    private static final Logger LOGGER = Logger.getLogger(MysqlPublisherDaoImpl.class);

    private static MysqlPublisherDaoImpl INSTANCE;
    private static ConnectionFactory connectionFactory;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findByNameQuery;
    private static String findAllQuery;

    private MysqlPublisherDaoImpl() {
        LOGGER.info("Initializing MysqlPublisherDaoImpl");

        connectionFactory = ConnectionFactory.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createPublisher");
        updateQuery = properties.getProperty("updatePublisherById");
        deleteQuery = properties.getProperty("deletePublisherById");
        findByIdQuery = properties.getProperty("findPublisherById");
        findAllQuery = properties.getProperty("findAllPublishers");
        findByNameQuery = properties.getProperty("findPublisherByName");
    }

    public static MysqlPublisherDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlPublisherDaoImpl();
        }
        return INSTANCE;
    }

    public Publisher createPublisher(Publisher publisher) {
        LOGGER.info("Creating new publisher");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, publisher.getTitle());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Publisher creation failed");
            }
            else {
                LOGGER.info("Publisher creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        publisher.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create publisher, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publisher;
    }

    public Publisher updatePublisher(Publisher publisher) {
        LOGGER.info("Updating publisher");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, publisher.getTitle());
            statement.setLong(2, publisher.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Publisher update failed");
            }
            else {
                LOGGER.info("Publisher updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publisher;
    }

    public boolean deletePublisherById(Long id) {
        LOGGER.info("Deleting publisher");
        boolean res = false;

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Publisher deletion failed");
            }
            else {
                LOGGER.info("Publisher deleted successfully");
                res = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    public Publisher findPublisherById(Long id) {
        LOGGER.info("Getting publisher with id " + id);
        Publisher publisher = null;

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                publisher = new Publisher();
                publisher.setId(result.getLong("id"));
                publisher.setTitle(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publisher;
    }

    @Override
    public List<Publisher> findAll() {
        LOGGER.info("Getting all publishers");
        List<Publisher> res = new ArrayList<>();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAllQuery);
            ResultSet result = statement.executeQuery();

            while(result.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(result.getLong("id"));
                publisher.setTitle(result.getString("name"));

                res.add(publisher);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    @Override
    public Publisher findPublisherByTitle(String title) {
        LOGGER.info("Getting category with name " + title);
        Publisher publisher = null;

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByNameQuery);
            statement.setString(1, title);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                publisher = new Publisher();
                publisher.setId(result.getLong("id"));
                publisher.setTitle(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publisher;
    }
}
