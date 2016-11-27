package com.newsstand.dao;

import com.newsstand.model.user.User;
import org.apache.log4j.Logger;

public final class UserDao implements GenericDao<User, Long> {

    private static final UserDao instance = new UserDao();
    private static Logger logger = Logger.getLogger(UserDao.class);

    private UserDao() {
        logger.info("Initializing UserDao");
    }

    public UserDao getInstance() {
        return instance;
    }

    public void saveOrUpdate(User obj) {

    }

    public void deleteById(Long aLong) {

    }

    public User getEntityById(Long aLong) {
        return null;
    }
}
