package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Category;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.magazine.Publisher;
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
import java.util.List;

/**
 * This class is used to handle POST requests to update magazine.
 */
public class UpdateMagazineAdminCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(UpdateMagazineAdminCommand.class);

	private static MagazineService magazineService;
	private static PublisherService publisherService;
	private static CategoryService categoryService;

	private static String magazinesPage;
	private static String loginPage;

	public UpdateMagazineAdminCommand(){
		LOGGER.info("Initializing UpdateMagazineAdminCommand");

		magazineService = MagazineServiceImpl.getInstance();
		publisherService = PublisherServiceImpl.getInstance();
		categoryService = CategoryServiceImpl.getInstance();

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
		else if(request.getParameter("id") != null && request.getParameter("title") != null &&
				request.getParameter("quantity") != null && request.getParameter("price") != null &&
				request.getParameter("publisher") != null && request.getParameter("category") != null &&
				request.getParameter("description") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));

				Category category = new Category();
				category.setId(Long.parseLong(request.getParameter("category")));

				Publisher publisher = new Publisher();
				publisher.setId(Long.parseLong(request.getParameter("publisher")));

				Magazine magazine = new Magazine();
				magazine.setId(id);
				magazine.setTitle(request.getParameter("title"));
				magazine.setDescription(request.getParameter("description"));
				magazine.setPrice(Float.parseFloat(request.getParameter("price")));
				magazine.setQuantity(Long.parseLong(request.getParameter("quantity")));
				magazine.setCategory(category);
				magazine.setPublisher(publisher);

				magazineService.updateMagazine(magazine);

				request.setAttribute("updateSuccess", true);

				List<Magazine> page = magazineService.getPage(1, 10);
				request.setAttribute("page", page);
				request.setAttribute("pageNum", 1L);
				request.setAttribute("pageSize", 10L);
				request.setAttribute("currSize", page.size());

				resultPage = magazinesPage;
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id")
						                      + ", "
						                      + request.getParameter("category")
						                      + ", "
						                      + request.getParameter("publisher")
						                      + " to long");
			}

		}

		return resultPage;
	}
}
