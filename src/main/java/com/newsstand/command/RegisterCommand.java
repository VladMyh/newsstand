package com.newsstand.command;

import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to register user.
 */
public class RegisterCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

	private static UserService userService;
	private static CategoryService categoryService;
	private static MagazineService magazineService;

	private static String registerPage;
	private static String mainPage;

	public RegisterCommand(){
		LOGGER.info("Initializing RegisterCommand");

		userService = UserServiceImpl.getInstance();
		categoryService = CategoryServiceImpl.getInstance();
		magazineService = MagazineServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		registerPage = properties.getProperty("registerPage");
		mainPage = properties.getProperty("mainPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		String resultPage = registerPage;

		if(request.getParameter("fname") != null && request.getParameter("lname") != null &&
			request.getParameter("email") != null && request.getParameter("password") != null &&
			request.getParameter("address") != null &&
			userService.checkEmailAvailability(request.getParameter("email"))){

			LOGGER.info("New user registration");

			User user = new User();
			user.setFirstName(request.getParameter("fname"));
			user.setLastName(request.getParameter("lname"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setAddress(request.getParameter("address"));
			user.setUserType(UserType.USER);

			if(userService.registerUser(user)) {
				request.setAttribute("categories", categoryService.findAll());
				request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

				resultPage = mainPage;
			}
		}

		return resultPage;
	}
}
