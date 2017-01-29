package com.newsstand.command.admin.category;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to delete category.
 */
public class DeleteCategoryAdminCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(CategoriesAdminPageCommand.class);

    private static CategoryService categoryService;

    private static String categoriesPage;
    private static String loginPage;

    public DeleteCategoryAdminCommand(){
        LOGGER.info("Initializing DeleteCategoryAdminCommand");

        categoryService = CategoryServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        categoriesPage = properties.getProperty("adminCategoriesPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = categoriesPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else if(request.getParameter("id") != null) {
            Long id = Long.parseLong(request.getParameter("id"));

            request.setAttribute("deletionSuccess", categoryService.deleteCategoryById(id));
            request.setAttribute("categories", categoryService.findAll());
        }

        return resultPage;
    }
}
