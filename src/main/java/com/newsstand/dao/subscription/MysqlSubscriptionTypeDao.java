package com.newsstand.dao.subscription;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.model.subscription.SubscriptionType;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlSubscriptionTypeDao implements SubscriptionTypeDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlSubscriptionTypeDao.class);

    private static MysqlSubscriptionTypeDao INSTANCE;
    private static ConnectionFactory connectionFactory;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findQuery;

    private MysqlSubscriptionTypeDao() {
        LOGGER.info("Initializing MysqlSubscriptionTypeDao");

        connectionFactory = ConnectionFactory.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createSubscriptionType");
        updateQuery = properties.getProperty("updateSubscriptionTypeById");
        deleteQuery = properties.getProperty("deleteSubscriptionTypeById");
        findQuery = properties.getProperty("findSubscriptionTypeById");
    }

    public static MysqlSubscriptionTypeDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlSubscriptionTypeDao();
        }
        return INSTANCE;
    }

    @Override
    public SubscriptionType createSubscriptionType(SubscriptionType subscriptionType) {
        LOGGER.info("Creating new subscription type");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, subscriptionType.getName());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Subscription type creation failed");
            }
            else {
                LOGGER.info("Subscription type creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subscriptionType.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create subscription type, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscriptionType;
    }

    @Override
    public SubscriptionType updateSubscriptionType(SubscriptionType subscriptionType) {
        LOGGER.info("Updating subscription type");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, subscriptionType.getName());
            statement.setLong(2, subscriptionType.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Subscription type update failed");
            }
            else {
                LOGGER.info("Subscription type updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscriptionType;
    }

    @Override
    public void deleteSubscriptionTypeById(Long id) {
        LOGGER.info("Deleting subscription type");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Subscription type deletion failed");
            }
            else {
                LOGGER.info("Subscription type deleted successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public SubscriptionType findSubscriptionTypeById(Long id) {
        LOGGER.info("Getting subscription type with id " + id);
        SubscriptionType subscriptionType = new SubscriptionType();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                subscriptionType.setId(result.getLong("id"));
                subscriptionType.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscriptionType;
    }
}
