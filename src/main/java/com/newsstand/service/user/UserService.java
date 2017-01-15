package com.newsstand.service.user;

import com.newsstand.dto.UserDto;

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
    boolean registerUser(UserDto user);

    /**
     * This method gets user object based on credentials.
     *
     * @param email    User email.
     * @param password User password.
     * @return         User object if user is found, otherwise null.
     */
    UserDto getUserByCredentials(String email, String password);
}
