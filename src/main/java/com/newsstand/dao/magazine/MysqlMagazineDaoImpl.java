package com.newsstand.dao.magazine;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.dao.publisher.MysqlPublisherDaoImpl;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlMagazineDaoImpl implements MagazineDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlMagazineDaoImpl.class);

    private static MysqlMagazineDaoImpl INSTANCE;
    private static ConnectionFactory connectionFactory;
    private MysqlCategoryDaoImpl categoryDao;
    private MysqlPublisherDaoImpl publisherDao;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findByIdQuery;
    private static String findLastQuery;
    private static String findPageByCategoryQuery;
    private static String findPageByPublisherQuery;
    private static String findPage;

    private MysqlMagazineDaoImpl() {
        LOGGER.info("Initializing MysqlMagazineDaoImpl");

        connectionFactory = ConnectionFactory.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createMagazine");
        updateQuery = properties.getProperty("updateMagazineById");
        deleteQuery = properties.getProperty("deleteMagazineById");
        findByIdQuery = properties.getProperty("findMagazineById");
        findLastQuery = properties.getProperty("findLastMagazines");
        findPageByCategoryQuery = properties.getProperty("findPageByCategory");
        findPageByPublisherQuery = properties.getProperty("findPageByPublisher");
        findPage = properties.getProperty("findPage");

        categoryDao = MysqlCategoryDaoImpl.getInstance();
        publisherDao = MysqlPublisherDaoImpl.getInstance();
    }

    public static MysqlMagazineDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlMagazineDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public Magazine createMagazine(Magazine magazine) {
        LOGGER.info("Creating new magazine");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, magazine.getTitle());
            statement.setLong(2, magazine.getQuantity());
            statement.setBigDecimal(3, new BigDecimal(magazine.getPrice()));
            statement.setLong(4, magazine.getPublisher().getId());
            statement.setLong(5, magazine.getCategory().getId());
            statement.setString(6, magazine.getDescription());


            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Magazine creation failed");
            }
            else {
                LOGGER.info("Magazine creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        magazine.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create magazine, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return magazine;
    }

    @Override
    public Magazine updateMagazine(Magazine magazine) {
        LOGGER.info("Updating magazine");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, magazine.getTitle());
            statement.setLong(2, magazine.getQuantity());
            statement.setBigDecimal(3, new BigDecimal(magazine.getPrice()));
            statement.setLong(4, magazine.getPublisher().getId());
            statement.setLong(5, magazine.getCategory().getId());
            statement.setString(6, magazine.getDescription());
            statement.setLong(7, magazine.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Magazine update failed");
            }
            else {
                LOGGER.info("Magazine updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return magazine;
    }

    @Override
    public boolean deleteMagazineById(Long id) {
        LOGGER.info("Deleting magazine");
        boolean res = false;

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Magazine deletion failed");
            }
            else {
                LOGGER.info("Magazine deleted successfully");
                res = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    @Override
    public Magazine findMagazineById(Long id) {
        LOGGER.info("Getting magazine with id " + id);
        Magazine magazine = null;

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                magazine = new Magazine();
                magazine.setId(result.getLong("id"));
                magazine.setTitle(result.getString("name"));
                magazine.setQuantity(result.getLong("quantity"));
                magazine.setPrice(result.getBigDecimal("price").floatValue());
                magazine.setPublisher(publisherDao.findPublisherById(result.getLong("publisherId")));
                magazine.setCategory(categoryDao.findCategoryById(result.getLong("categoryId")));
                magazine.setDescription(result.getString("description"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return magazine;
    }

    @Override
    public List<Magazine> findLastNMagazines(Integer limit) {
        LOGGER.info("Getting " + limit + " latest magazines");
        List<Magazine> res = new ArrayList<>();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findLastQuery);
            statement.setInt(1, limit);

            ResultSet result = statement.executeQuery();

            res = getMagazines(result);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    @Override
    public List<Magazine> findPageByCategory(Long categoryId, Integer offset, Integer size) {
        LOGGER.info("Getting page with offset " + offset + ", size " + size + " of category id " + categoryId);
        List<Magazine> res = new ArrayList<>();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findPageByCategoryQuery);
            statement.setLong(1, categoryId);
            statement.setInt(2, offset);
            statement.setInt(3, size);

            ResultSet result = statement.executeQuery();

            res = getMagazines(result);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    @Override
    public List<Magazine> findPageByPublisher(Long publisherId, Integer offset, Integer size) {
        LOGGER.info("Getting page with offset " + offset + ", size " + size + " of publisher id " + publisherId);
        List<Magazine> res = new ArrayList<>();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findPageByPublisherQuery);
            statement.setLong(1, publisherId);
            statement.setInt(2, offset);
            statement.setInt(3, size);

            ResultSet result = statement.executeQuery();

            res = getMagazines(result);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    @Override
    public List<Magazine> findPage(Integer offset, Integer size) {
        LOGGER.info("Getting page with offset " + offset + ", size " + size);
        List<Magazine> res = new ArrayList<>();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findPage);
            statement.setInt(1, offset);
            statement.setInt(2, size);

            ResultSet result = statement.executeQuery();

            res = getMagazines(result);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }

    private List<Magazine> getMagazines(ResultSet result) {
        List<Magazine> res = new ArrayList<>();

        try {
            while (result.next()) {
                Magazine magazine = new Magazine();
                magazine.setId(result.getLong("id"));
                magazine.setTitle(result.getString("name"));
                magazine.setQuantity(result.getLong("quantity"));
                magazine.setPrice(result.getBigDecimal("price").floatValue());
                magazine.setPublisher(publisherDao.findPublisherById(result.getLong("publisherId")));
                magazine.setCategory(categoryDao.findCategoryById(result.getLong("categoryId")));
                magazine.setDescription(result.getString("description"));

                res.add(magazine);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return res;
    }
}
