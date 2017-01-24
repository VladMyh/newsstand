package com.newsstand.command.admin.category;

import com.newsstand.command.ServletCommand;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page used to view all categories.
 */
public class CategoriesAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(CategoriesAdminPageCommand.class);

    private static CategoryService categoryService;

    private static String page;

    public CategoriesAdminPageCommand(){
        LOGGER.info("Initializing CategoriesAdminPageCommand");

        categoryService = CategoryServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminCategoriesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        request.setAttribute("categories", categoryService.getAllCategories());

        return page;
    }
}
