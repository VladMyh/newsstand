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
 * This class is used to handle POST requests to update category.
 */
public class UpdateCategoryAdminCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UpdateCategoryAdminCommand.class);

    private static CategoryService categoryService;

    private static String categoriesPage;
    private static String loginPage;

    public UpdateCategoryAdminCommand(){
        LOGGER.info("Initializing UpdateCategoryAdminPageCommand");

        categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());

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
        else if(request.getParameter("id") != null && request.getParameter("name") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Category category = new Category();
                category.setId(id);
                category.setName(request.getParameter("name"));
                categoryService.updateCategory(category);

                request.setAttribute("updateSuccess", true);
                request.setAttribute("categories", categoryService.findAll());
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }

        }

        return resultPage;
    }
}
