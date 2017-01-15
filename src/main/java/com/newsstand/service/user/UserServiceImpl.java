package com.newsstand.service.user;

import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.dao.user.UserDao;
import com.newsstand.dto.UserDto;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import org.apache.log4j.Logger;

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
    public boolean registerUser(UserDto userDto) {
        LOGGER.info("New user registration");

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserType(UserType.USER);

        return userDao.createUser(user).getId() != null;
    }
}
