package com.newsstand.dao.user;

import com.newsstand.model.user.User;

public interface UserDao {
	User createUser(User user);
	User updateUser(User user);
	void deleteUserById(Long id);
	User findUserById(Long id);
}
