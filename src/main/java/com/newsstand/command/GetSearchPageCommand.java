package com.newsstand.command;

import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
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

/**
 * This class is used to handle GET requests to the magazine search page.
 */
public class GetSearchPageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetSearchPageCommand.class);

	private static CategoryService categoryService;
	private static MagazineService magazineService;

	private static String magazinesPage;
	private static String errorPage;

	public GetSearchPageCommand(){
		LOGGER.info("Initializing GetSearchPageCommand");

		categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());
		magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
				                                  MysqlImageDaoImpl.getInstance());

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

				Page<Magazine> page = magazineService.getPageByName(query, pageNum, size);

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
