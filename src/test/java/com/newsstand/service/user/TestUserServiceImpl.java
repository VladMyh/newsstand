package com.newsstand.service.user;

import com.newsstand.dao.user.UserDao;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.util.Page;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TestUserServiceImpl {

	@Mock
	private UserDao userDao;

	private UserService userService;

	@Before
	public void init() {
		userService = new UserServiceImpl(userDao);
	}

	@Test
	public void givenUsedEmail_whenCallingCheckEmailAvailability_shouldReturnFalse() {
		User user = mock(User.class);
		when(userDao.findUserByEmail("e@mail.com")).thenReturn(user);

		Boolean test = userService.checkEmailAvailability("e@mail.com");

		verify(userDao, only()).findUserByEmail(anyString());
		Assert.assertFalse(test);
	}

	@Test
	public void givenUnusedEmail_whenCallingCheckEmailAvailability_shouldReturnTrue() {
		when(userDao.findUserByEmail("e@mail.com")).thenReturn(null);

		Boolean test = userService.checkEmailAvailability("e@mail.com");

		verify(userDao, only()).findUserByEmail(anyString());
		Assert.assertTrue(test);
	}

	@Test
	public void givenNull_whenCallingCheckEmailAvailability_shouldReturnFalse() {
		Boolean test = userService.checkEmailAvailability(null);

		verify(userDao, never()).findUserByEmail(anyString());
		Assert.assertFalse(test);
	}

	@Test
	public void givenUser_whenCallingRegisterUser_shouldReturnTrue() {
		User user = mock(User.class);
		User registered = mock(User.class);
		registered.setId(1L);
		when(userDao.createUser(user)).thenReturn(registered);

		Boolean test = userService.registerUser(user);

		verify(userDao, only()).createUser(user);
		Assert.assertTrue(test);
	}

	@Test
	public void givenNull_whenCallingRegisterUser_shouldReturnFalse() {
		Boolean test = userService.registerUser(null);

		verify(userDao, never()).createUser(any());
		Assert.assertFalse(test);
	}

	@Test
	public void givenCredentials_whenCalllingGetUserByCredentials_shouldReturnUser() {
		User user = mock(User.class);
		when(userDao.findUserByEmailAndPassword("email", "password")).thenReturn(user);

		User test = userService.getUserByCredentials("email", "password");

		verify(userDao, only()).findUserByEmailAndPassword(anyString(), anyString());
		Assert.assertNotNull(test);
	}

	@Test
	public void givenNullCredentials_whenCalllingGetUserByCredentials_shouldReturnNull() {
		User test = userService.getUserByCredentials("email", null);

		verify(userDao, never()).findUserByEmailAndPassword(anyString(), anyString());
		Assert.assertNull(test);
	}

	@Test
	public void givenValidPageSizeUserType_whenCallingGetPageByUserType_shouldReturnPage() {
		List<User> items = new ArrayList<>();
		items.add(mock(User.class));
		items.add(mock(User.class));
		items.add(mock(User.class));
		when(userDao.findPageByUserType(UserType.USER, 0, 6)).thenReturn(items);

		Page<User> page = userService.getPageByUserType(1, 6, UserType.USER);

		verify(userDao, only()).findPageByUserType(UserType.USER, 0, 6);
		Assert.assertNotNull(page);
	}

	@Test
	public void givenNullPageSizeUserType_whenCallingGetPageByUserType_shouldReturnNull() {
		Page<User> page = userService.getPageByUserType(null, null, UserType.USER);

		verify(userDao, never()).findPageByUserType(any(UserType.class), anyInt(), anyInt());
		Assert.assertNull(page);
	}

	@Test
	public void givenInvalidPageSizeUserType_whenCallingGetPageByUserType_shouldReturnNull() {
		Page<User> page = userService.getPageByUserType(-100, -22, UserType.USER);

		verify(userDao, never()).findPageByUserType(any(UserType.class), anyInt(), anyInt());
		Assert.assertNull(page);
	}

	@Test
	public void givenValidEmail_whenCallingFindUserByEmail_shouldReturnUser() {
		User user = mock(User.class);
		when(userDao.findUserByEmail("e@mail.com")).thenReturn(user);

		User test = userService.findUserByEmail("e@mail.com");

		verify(userDao, only()).findUserByEmail(anyString());
		Assert.assertNotNull(test);
	}

	@Test
	public void givenInvalidEmail_whenCallingFindUserByEmail_shouldReturnNull() {
		when(userDao.findUserByEmail("e@mail.com")).thenReturn(null);

		User test = userService.findUserByEmail("e@mail.com");

		verify(userDao, only()).findUserByEmail(anyString());
		Assert.assertNull(test);
	}

	@Test
	public void givenNullEmail_whenCallingFindUserByEmail_shouldReturnNull() {
		User test = userService.findUserByEmail(null);

		verify(userDao, never()).findUserByEmail(anyString());
		Assert.assertNull(test);
	}

	@Test
	public void givenValidId_whenCallingFindUserById_shouldReturnUser() {
		User user = mock(User.class);
		when(userDao.findUserById(1L)).thenReturn(user);

		User test = userService.findUserById(1L);

		verify(userDao, only()).findUserById(anyLong());
		Assert.assertNotNull(test);
	}

	@Test
	public void givenInvalidId_whenCallingFindUserById_shouldReturnNull() {
		when(userDao.findUserById(9999L)).thenReturn(null);

		User test = userService.findUserById(9999L);

		verify(userDao, only()).findUserById(anyLong());
		Assert.assertNull(test);
	}

	@Test
	public void givenNullId_whenCallingFindUserById_shouldReturnNull() {
		User test = userService.findUserById(null);

		verify(userDao, never()).findUserById(anyLong());
		Assert.assertNull(test);
	}
}
