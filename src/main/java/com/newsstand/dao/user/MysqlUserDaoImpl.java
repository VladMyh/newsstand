package com.newsstand.dao.user;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.sql.*;

public class MysqlUserDaoImpl implements UserDao{
	private static final Logger LOGGER = Logger.getLogger(MysqlUserDaoImpl.class);

	private static MysqlUserDaoImpl INSTANCE;
	private static ConnectionFactory connectionFactory;

	private static String createQuery;
	private static String updateQuery;
	private static String deleteQuery;
	private static String findByIdQuery;

	private MysqlQueryProperties properties;

	private MysqlUserDaoImpl() {
		LOGGER.info("Initializing MysqlUserDaoImpl");

		connectionFactory = ConnectionFactory.getInstance();
		properties = MysqlQueryProperties.getInstance();

		createQuery = properties.getProperty("createUser");
		updateQuery = properties.getProperty("updateUserById");
		deleteQuery = properties.getProperty("deleteUserById");
		findByIdQuery = properties.getProperty("findUserById");
	}

	public static MysqlUserDaoImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MysqlUserDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public User createUser(User user) {
		LOGGER.info("Creating new user");

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setLong(5, user.getUserType().ordinal() + 1);

			int affectedRows = statement.executeUpdate();

			if(affectedRows == 0) {
				LOGGER.info("User creation failed");
			}
			else {
				LOGGER.info("User creation successful");

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						user.setId(generatedKeys.getLong(1));
					}
					else {
						LOGGER.error("Failed to create user, no ID obtained.");
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return user;
	}

	@Override
	public User updateUser(User user) {
		LOGGER.info("Updating user");

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(updateQuery);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getEmail());
			statement.setLong(5, user.getUserType().ordinal() + 1);
			statement.setLong(6, user.getId());

			boolean result = statement.execute();

			if(result) {
				LOGGER.info("User update failed");
			}
			else {
				LOGGER.info("User updated successfully");
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return user;
	}

	@Override
	public void deleteUserById(Long id) {
		LOGGER.info("Deleting user");

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
			statement.setLong(1, id);

			boolean result = statement.execute();

			if(result) {
				LOGGER.info("User deletion failed");
			}
			else {
				LOGGER.info("User deleted successfully");
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public User findUserById(Long id) {
		LOGGER.info("Getting user with id " + id);
		User user = new User();

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findByIdQuery);
			statement.setLong(1, id);

			ResultSet result = statement.executeQuery();

			if(result.next()) {
				user.setId(result.getLong("id"));
				user.setFirstName(result.getString("firstName"));
				user.setLastName(result.getString("lastName"));
				user.setPassword(result.getString("password"));
				user.setEmail(result.getString("email"));
				user.setUserType(UserType.values()[result.getInt("userTypeId") - 1]);
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return user;
	}
}
