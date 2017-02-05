package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.image.ImageService;
import com.newsstand.service.image.ImageServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle GET requests to delete magazines.
 */
public class DeleteMagazineAdminCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(DeleteMagazineAdminCommand.class);

	private static MagazineService magazineService;

	private static String magazinesPage;
	private static String loginPage;

	public DeleteMagazineAdminCommand(){
		LOGGER.info("Initializing DeleteMagazineAdminCommand");

		magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
				                                  MysqlImageDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		magazinesPage = properties.getProperty("adminMagazinesPage");
		loginPage = properties.getProperty("loginPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = magazinesPage;

		if(request.getSession().getAttribute("authenticated") != null &&
				request.getSession().getAttribute("authenticated").equals(true) &&
				!request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
			LOGGER.info("User not authorized");
			resultPage = loginPage;
		}
		else if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));

				request.setAttribute("deletionSuccess", magazineService.deleteMagazineById(id));
				Page<Magazine> page = magazineService.getPage(1, 10);

				request.setAttribute("page", page);
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
			}
		}

		return resultPage;
	}
}
