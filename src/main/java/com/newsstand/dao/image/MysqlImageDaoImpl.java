package com.newsstand.dao.image;

import com.newsstand.connection.ConnectionFactory;
import com.newsstand.properties.MysqlQueryProperties;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.*;

public class MysqlImageDaoImpl implements ImageDao {

	private static final Logger LOGGER = Logger.getLogger(MysqlImageDaoImpl.class);

	private static MysqlImageDaoImpl INSTANCE;
	private static ConnectionFactory connectionFactory;

	private static String findImageQuery;
	private static String createQuery;
	private static String deleteQuery;

	private MysqlImageDaoImpl() {
		LOGGER.info("Initializing MysqlImageDaoImpl");

		connectionFactory = ConnectionFactory.getInstance();
		MysqlQueryProperties properties = MysqlQueryProperties.getInstance();

		findImageQuery = properties.getProperty("findImageById");
		createQuery = properties.getProperty("createImage");
		deleteQuery = properties.getProperty("deleteImageById");
	}

	public static MysqlImageDaoImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MysqlImageDaoImpl();
		}
		return INSTANCE;
	}

	@Override
	public byte[] findImageById(Long id) {
		LOGGER.info("Finding image by id " + id);
		byte[] result = new byte[0];

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(findImageQuery);
			statement.setLong(1, id);

			ResultSet res = statement.executeQuery();

			if(res.next()) {
				result = res.getBytes("image");
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return result;
	}

	@Override
	public Long createImage(InputStream inputStream) {
		LOGGER.info("Creating image");
		Long id = null;

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setBlob(1, inputStream);

			int affectedRows = statement.executeUpdate();

			if(affectedRows == 0) {
				LOGGER.info("Image creation failed");
			}
			else {
				LOGGER.info("Image creation successful");

				try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						id = generatedKeys.getLong(1);
					}
					else {
						LOGGER.error("No ID obtained.");
					}
				}
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return id;
	}

	@Override
	public boolean deleteImageById(Long id) {
		LOGGER.info("Deleting image by id " + id);
		boolean result = false;

		try(Connection connection = connectionFactory.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(deleteQuery);
			statement.setLong(1, id);

			boolean res = statement.execute();

			if(res) {
				LOGGER.info("Failed to delete image by id " + id);
			}
			else {
				LOGGER.info("Image deleted successfully");
				result = true;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		return result;
	}
}
