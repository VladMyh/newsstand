package com.newsstand.dao.publisher;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.model.magazine.Publisher;
import org.apache.log4j.Logger;

import java.sql.*;

public final class PublisherDaoImpl implements PublisherDao {
    private static final Logger LOGGER = Logger.getLogger(PublisherDaoImpl.class);

    private static PublisherDaoImpl INSTANCE;
    private static ConnectionFactory connectionFactory;

    private static String createQuery = "INSERT INTO publisher (name) VALUES (?)";
    private static String updateQuery = "UPDATE publisher SET name = ? WHERE id = ?";
    private static String deleteQuery = "DELETE FROM publisher WHERE id = ?";
    private static String getQuery = "SELECT * FROM publisher WHERE id = ?";

    private PublisherDaoImpl() {
        LOGGER.info("Initializing PublisherDao");

        connectionFactory = ConnectionFactory.getInstance();
    }

    public static PublisherDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new PublisherDaoImpl();
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
                publisher.setId(statement.getGeneratedKeys().getLong(1));
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

    public void deleteById(Long id) {
        LOGGER.info("Deleting publisher");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Publisher deletion failed");
            }
            else {
                LOGGER.info("Publisher deleted successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Publisher findPublisherById(Long id) {
        LOGGER.info("Getting publisher with id " + id);
        Publisher publisher = new Publisher();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            publisher.setId(result.getLong("id"));
            publisher.setTitle(result.getString("name"));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publisher;
    }
}
