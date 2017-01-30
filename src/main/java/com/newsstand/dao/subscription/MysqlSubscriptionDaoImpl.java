package com.newsstand.dao.subscription;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.dao.magazine.MagazineDao;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.dao.user.UserDao;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;

public class MysqlSubscriptionDaoImpl implements SubscriptionDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlSubscriptionDaoImpl.class);

    private static MysqlSubscriptionDaoImpl INSTANCE;
    private static ConnectionFactory connectionFactory;
    private MagazineDao magazineDao;
    private SubscriptionTypeDao subscriptionTypeDao;
    private UserDao userDao;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String checkIfUserSubscribedQuery;

    private MysqlSubscriptionDaoImpl() {
        LOGGER.info("Initializing MysqlSubscriptionDaoImpl");

        connectionFactory = ConnectionFactory.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createSubscription");
        updateQuery = properties.getProperty("updateSubscriptionById");
        deleteQuery = properties.getProperty("deleteSubscriptionById");
        findByIdQuery = properties.getProperty("findSubscriptionById");
        checkIfUserSubscribedQuery = properties.getProperty("checkIfUserSubscribed");

        magazineDao = MysqlMagazineDaoImpl.getInstance();
        subscriptionTypeDao = MysqlSubscriptionTypeDao.getInstance();
        userDao = MysqlUserDaoImpl.getInstance();
    }

    public static MysqlSubscriptionDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlSubscriptionDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public Subscription createSubscription(Subscription subscription) {
        LOGGER.info("Creating new subscription");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, subscription.getUser().getId());
            statement.setLong(2, subscription.getMagazine().getId());
            statement.setLong(3, subscription.getType().getId());
            statement.setDate(4, subscription.getStartDate());
            statement.setDate(5, subscription.getEndDate());
            statement.setBigDecimal(6, new BigDecimal(subscription.getPrice()));

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Subscription creation failed");
            }
            else {
                LOGGER.info("Subscription creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        subscription.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create subscription, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscription;
    }

    @Override
    public Subscription updateSubscription(Subscription subscription) {
        LOGGER.info("Updating subscription");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setLong(1, subscription.getUser().getId());
            statement.setLong(2, subscription.getMagazine().getId());
            statement.setLong(3, subscription.getType().getId());
            statement.setDate(4, subscription.getStartDate());
            statement.setDate(5, subscription.getEndDate());
            statement.setBigDecimal(6, new BigDecimal(subscription.getPrice()));

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Subscription update failed");
            }
            else {
                LOGGER.info("Subscription updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscription;
    }

    @Override
    public void deleteSubscriptionById(Long id) {
        LOGGER.info("Deleting subscription");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Subscription deletion failed");
            }
            else {
                LOGGER.info("Subscription deleted successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Subscription findSubscriptionById(Long id) {
        LOGGER.info("Getting magazine with id " + id);
        Subscription subscription = new Subscription();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                subscription.setId(result.getLong("id"));
                subscription.setUser(userDao.findUserById(result.getLong("userId")));
                subscription.setMagazine(magazineDao.findMagazineById(result.getLong("magazineId")));
                subscription.setType(subscriptionTypeDao.findSubscriptionTypeById(result.getLong("subscriptionTypeId")));
                subscription.setStartDate(result.getDate("startDate"));
                subscription.setEndDate(result.getDate("endDate"));
                subscription.setPrice(result.getBigDecimal("price").floatValue());
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return subscription;
    }

    @Override
    public boolean checkIfUserSubscribed(Long userId, Long magazineId) {
        LOGGER.info("Checking if user " + userId + " is subscribed to magazine " + magazineId);
        boolean result = false;

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(checkIfUserSubscribedQuery);
            statement.setLong(1, userId);
            statement.setLong(2, magazineId);

            ResultSet res = statement.executeQuery();

            if(res.next()) {
                result = true;
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return result;
    }
}
