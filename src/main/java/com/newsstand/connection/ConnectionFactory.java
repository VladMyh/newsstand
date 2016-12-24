package com.newsstand.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static ConnectionFactory connectionFactory = null;
    private DataSource dataSource = null;

    private ConnectionFactory() {
        LOGGER.info("Initializing connectionFactory class");

        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            dataSource = (DataSource) envCtx.lookup("jdbc/newsstand");
        } catch (NamingException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public Connection getConnection() throws SQLException {
        LOGGER.info("Getting connection");
        return dataSource.getConnection();
    }

    public static synchronized ConnectionFactory getInstance() {
        if(connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory;
    }
}
