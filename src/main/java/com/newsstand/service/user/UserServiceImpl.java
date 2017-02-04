package com.newsstand.service.user;

import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.dao.user.UserDao;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private static UserServiceImpl INSTANCE;
    private static UserDao userDao;

    private UserServiceImpl() {
        LOGGER.info("Initializing UserServiceImpl");

        userDao = MysqlUserDaoImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean checkEmailAvailability(String email) {
        LOGGER.info("Checking availability of email");

        User user = userDao.findUserByEmail(email);
        return user == null;
    }

    @Override
    public boolean registerUser(User user) {
        LOGGER.info("New user registration");

        return userDao.createUser(user).getId() != null;
    }

    @Override
    public User getUserByCredentials(String email, String password) {
        LOGGER.info("Getting user by credentials");

        return userDao.findUserByEmailAndPassword(email, password);
    }

    @Override
    public Page<User> getPageByUserType(Integer page, Integer size, UserType userType) {
        LOGGER.info("Getting page number " + page + ", of size " + size + ", for user type " + userType.name());

        List<User> items =  userDao.findPageByUserType(userType, (page - 1) * size, size);
        return new Page<>(items, page, size);
    }

    @Override
    public User findUserByEmail(String email) {
        LOGGER.info("Finding user by email " + email);

        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        LOGGER.info("Finding user by id " + id);

        return userDao.findUserById(id);
    }
}
