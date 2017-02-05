package com.newsstand.command;

import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.dao.user.MysqlUserDaoImpl;
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
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * This class is used to handle POST requests to authenticate users.
 */
public class LoginCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

	private static UserService userService;
	private static CategoryService categoryService;
	private static MagazineService magazineService;

	private static String loginPage;
	private static String mainPage;

	public LoginCommand(){
		LOGGER.info("Initializing LoginCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
		categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());
		magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
				                                  MysqlImageDaoImpl.getInstance());
		MappingProperties properties = MappingProperties.getInstance();
		loginPage = properties.getProperty("loginPage");
		mainPage = properties.getProperty("mainPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		String resultPage = loginPage;

		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			User user = userService.getUserByCredentials(request.getParameter("email"),
					                                     request.getParameter("password"));

			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("username", user.getFirstName() + " " + user.getLastName());
				session.setAttribute("authenticated", true);
				session.setAttribute("role", user.getUserType().name());

				request.setAttribute("categories", categoryService.findAll());
				request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

				resultPage = mainPage;
			}
			else {
				request.setAttribute("loginSuccess", false);
			}
		}

		return resultPage;
	}
}
