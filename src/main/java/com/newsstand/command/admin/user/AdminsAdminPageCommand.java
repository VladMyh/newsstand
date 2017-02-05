package com.newsstand.command.admin.user;

import com.newsstand.command.ServletCommand;
import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to GET requests to the admin page to view admins.
 */
public class AdminsAdminPageCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AdminsAdminPageCommand.class);

	private static UserService userService;

	private static String page;

	public AdminsAdminPageCommand(){
		LOGGER.info("Initializing AdminsAdminPageCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		page = properties.getProperty("adminAdminsPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		try {
			Integer pageNum = Integer.parseInt(request.getParameter("p"));
			Integer size = Integer.parseInt(request.getParameter("s"));

			Page<User> page = userService.getPageByUserType(pageNum, size, UserType.ADMIN);

			request.setAttribute("page", page);
		}
		catch (NumberFormatException ex) {
			LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
					+ request.getParameter("s") +" to long");
		}


		return page;
	}
}
