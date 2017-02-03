package com.newsstand.command.admin.user;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to create admin.
 */
public class AddAdminAdminCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(AddAdminAdminCommand.class);

	private static UserService userService;

	private static String addAdminPage;

	public AddAdminAdminCommand(){
		LOGGER.info("Initializing AddAdminAdminCommand");

		userService = UserServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		addAdminPage = properties.getProperty("adminAddAdminPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = addAdminPage;

		if(request.getParameter("fname") != null && request.getParameter("lname") != null &&
				request.getParameter("email") != null && request.getParameter("password") != null &&
				userService.checkEmailAvailability(request.getParameter("email"))) {
			User user = new User();
			user.setFirstName(request.getParameter("fname"));
			user.setLastName(request.getParameter("lname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setUserType(UserType.ADMIN);
			user.setAddress("");

			request.setAttribute("success", userService.registerUser(user));
		}

		return resultPage;
	}
}
