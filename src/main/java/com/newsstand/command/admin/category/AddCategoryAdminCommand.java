package com.newsstand.command.admin.category;

import com.newsstand.command.ServletCommand;
import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.model.magazine.Category;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to add new category.
 */
public class AddCategoryAdminCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(AddCategoryAdminCommand.class);

	private static CategoryService categoryService;

	private static String addCategoryPage;
	private static String loginPage;

	public AddCategoryAdminCommand(){
		LOGGER.info("Initializing AddCategoryAdminCommand");

		categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		addCategoryPage = properties.getProperty("adminAddCategoryPage");
		loginPage = properties.getProperty("loginPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = addCategoryPage;

		if(request.getParameter("name") != null) {
			Category category = new Category();
			category.setName(request.getParameter("name"));

			category = categoryService.createCategory(category);

			request.setAttribute("success", category != null);
		}

		return resultPage;
	}
}
