package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page used to edit magazine.
 */
public class EditMagazineAdminPageCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(EditMagazineAdminPageCommand.class);

	private static MagazineService magazineService;
	private static PublisherService publisherService;
	private static CategoryService categoryService;

	private static String editMagazinePage;
	private static String magazinedPage;
	private static String loginPage;

	public EditMagazineAdminPageCommand(){
		LOGGER.info("Initializing EditMagazineAdminPageCommand");

		magazineService = MagazineServiceImpl.getInstance();
		publisherService = PublisherServiceImpl.getInstance();
		categoryService = CategoryServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		editMagazinePage = properties.getProperty("adminEditMagazinePage");
		magazinedPage = properties.getProperty("adminMagazinesPage");
		loginPage = properties.getProperty("loginPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = editMagazinePage;

		if(request.getSession().getAttribute("authenticated") != null &&
				request.getSession().getAttribute("authenticated").equals(true) &&
				!request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
			LOGGER.info("User not authorized");
			resultPage = loginPage;
		}
		else if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));

				Magazine magazine = magazineService.findMagazineById(id);

				if(magazine != null) {
					request.setAttribute("magazine", magazine);
					request.setAttribute("categories", categoryService.findAll());
					request.setAttribute("publishers", publisherService.findAll());
				}
				else {
					resultPage = magazinedPage;
				}
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
			}

		}

		return resultPage;
	}
}
