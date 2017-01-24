package com.newsstand.command.admin.category;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Category;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page used to edit category.
 */
public class EditCategoryAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(EditCategoryAdminPageCommand.class);

    private static CategoryService categoryService;

    private static String editCategoryPage;
    private static String categoriesPage;
    private static String loginPage;

    public EditCategoryAdminPageCommand(){
        LOGGER.info("Initializing EditCategoryAdminPageCommand");

        categoryService = CategoryServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        editCategoryPage = properties.getProperty("adminEditCategoryPage");
        categoriesPage = properties.getProperty("adminCategoriesPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = editCategoryPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else if(request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Category category = categoryService.findCategoryById(id);

                if(category != null) {
                    request.setAttribute("category", category);
                }
                else {
                    resultPage = categoriesPage;
                }
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }

        }

        return resultPage;
    }
}
