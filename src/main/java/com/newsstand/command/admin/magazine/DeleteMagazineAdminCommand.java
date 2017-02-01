package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle POST requests to delete magazines.
 */
public class DeleteMagazineAdminCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(DeleteMagazineAdminCommand.class);

	private static MagazineService magazineService;

	private static String magazinesPage;
	private static String loginPage;

	public DeleteMagazineAdminCommand(){
		LOGGER.info("Initializing DeleteMagazineAdminCommand");

		magazineService = MagazineServiceImpl.getInstance();

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
			Long id = Long.parseLong(request.getParameter("id"));

			request.setAttribute("deletionSuccess", magazineService.deleteMagazineById(id));
			List<Magazine> page = magazineService.getPage(1L, 10L);
			request.setAttribute("page", page);
			request.setAttribute("pageNum", 1L);
			request.setAttribute("pageSize", 10L);
			request.setAttribute("currSize", page.size());
		}

		return resultPage;
	}
}
