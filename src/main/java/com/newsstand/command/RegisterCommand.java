package com.newsstand.command;

import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserBuilder;
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

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
		categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());
		magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
				                                  MysqlImageDaoImpl.getInstance());

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

			User user = new UserBuilder().setFirstName(request.getParameter("fname"))
					                     .setLastName(request.getParameter("lname"))
					                     .setEmail(request.getParameter("email"))
					                     .setPassword(request.getParameter("password"))
					                     .setAddress(request.getParameter("address"))
					                     .setUserType(UserType.USER)
					                     .build();

			if(userService.registerUser(user)) {
				request.setAttribute("categories", categoryService.findAll());
				request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

				resultPage = mainPage;
			}
		}

		return resultPage;
	}
}
