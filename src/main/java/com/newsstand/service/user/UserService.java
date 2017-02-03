package com.newsstand.service.user;

import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;

import java.util.List;

public interface UserService {
    /**
     * This method check weather email is already used.
     *
     * @param email Email to check.
     * @return      True if email is unused, otherwise false.
     */
    boolean checkEmailAvailability(String email);

    /**
     * This method registers new user.
     *
     * @param user User to register.
     * @return     Updated user object.
     */
    boolean registerUser(User user);

    /**
     * This method gets user object based on credentials.
     *
     * @param email    User email.
     * @param password User password.
     * @return         User object if user is found, otherwise null.
     */
    User getUserByCredentials(String email, String password);

    /**
     * This method returns a page of users by user type.
     *
     * @param page     Number of the page, starts from 1.
     * @param size     Size of the page.
     * @param userType User type of the users to find.
     * @return         A list of users.
     */
    List<User> getPageByUserType(Integer page, Integer size, UserType userType);

    /**
     * This method finds user by email.
     *
     * @param email Email of the user to find.
     * @return      User found by email, otherwise null.
     */
    User findUserByEmail(String email);
}
