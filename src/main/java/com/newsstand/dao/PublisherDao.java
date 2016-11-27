package com.newsstand.dao;

import com.newsstand.model.magazine.Publisher;
import com.newsstand.model.user.User;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public final class PublisherDao implements GenericDao<Publisher, Long>{

    private static final PublisherDao instance = new PublisherDao();
    private static Logger logger = Logger.getLogger(Publisher.class);
    private static Connection connection;

    private PublisherDao() {
        logger.info("Initializing PublisherDao");

        Context initCtx = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            DataSource ds = (DataSource) envCtx.lookup("jdbc/EmployeeDB");
            connection = ds.getConnection();
        } catch (NamingException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

    }

    public void saveOrUpdate(Publisher obj) {


    }

    public void deleteById(Long aLong) {

    }

    public User getEntityById(Long aLong) {
        return null;
    }
}
