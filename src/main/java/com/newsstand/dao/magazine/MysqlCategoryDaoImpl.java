package com.newsstand.dao.magazine;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.model.magazine.Category;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlCategoryDaoImpl implements CategoryDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlCategoryDaoImpl.class);

    private static MysqlCategoryDaoImpl INSTANCE;
    private static ConnectionFactory connectionFactory;

    private static String createQuery;
    private static String updateQuery;
    private static String deleteQuery;
    private static String findQuery;

    private MysqlCategoryDaoImpl() {
        LOGGER.info("Initializing MysqlCategoryDaoImpl");

        connectionFactory = ConnectionFactory.getInstance();
        MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

        createQuery = properties.getProperty("createCategory");
        updateQuery = properties.getProperty("updateCategoryById");
        deleteQuery = properties.getProperty("deleteCategoryById");
        findQuery = properties.getProperty("findCategoryById");
    }

    public static MysqlCategoryDaoImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new MysqlCategoryDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public Category createCategory(Category category) {
        LOGGER.info("Creating new category");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());

            int affectedRows = statement.executeUpdate();

            if(affectedRows == 0) {
                LOGGER.info("Category creation failed");
            }
            else {
                LOGGER.info("Category creation successful");

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        category.setId(generatedKeys.getLong(1));
                    }
                    else {
                        LOGGER.error("Failed to create category, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        LOGGER.info("Updating category");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Category update failed");
            }
            else {
                LOGGER.info("Category updated successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return category;
    }

    @Override
    public void deleteCategoryById(Long id) {
        LOGGER.info("Deleting category");

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);

            boolean result = statement.execute();

            if(result) {
                LOGGER.info("Category deletion failed");
            }
            else {
                LOGGER.info("Category deleted successfully");
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Category findCategoryById(Long id) {
        LOGGER.info("Getting category with id " + id);
        Category category = new Category();

        try(Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findQuery);
            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()) {
                category.setId(result.getLong("id"));
                category.setName(result.getString("name"));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return category;
    }
}
