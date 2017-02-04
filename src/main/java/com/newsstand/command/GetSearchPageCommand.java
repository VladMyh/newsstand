package com.newsstand.command;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetSearchPageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetSearchPageCommand.class);

	private static CategoryService categoryService;
	private static MagazineService magazineService;

	private static String magazinesPage;
	private static String errorPage;

	public GetSearchPageCommand(){
		LOGGER.info("Initializing GetSearchPageCommand");

		categoryService = CategoryServiceImpl.getInstance();
		magazineService = MagazineServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		magazinesPage = properties.getProperty("searchMagazinesPage");
		errorPage = properties.getProperty("error404Page");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		String resultPage = errorPage;

		if(request.getParameter("q") != null && request.getParameter("p") != null &&
			request.getParameter("s") != null) {

			try {
				String query = request.getParameter("q");
				Integer pageNum = Integer.parseInt(request.getParameter("p"));
				Integer size = Integer.parseInt(request.getParameter("s"));

				List<Magazine> items = magazineService.getPageByName(query, pageNum, size);
				Page<Magazine> page = new Page<>(items, pageNum, size);

				request.setAttribute("categories", categoryService.findAll());
				request.setAttribute("page", page);
				request.setAttribute("query", query);

				resultPage = magazinesPage;
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("catId") + ", "
						+ request.getParameter("p") + ", "
						+ request.getParameter("s")+ " to long");
			}
		}

		return resultPage;
	}
}
