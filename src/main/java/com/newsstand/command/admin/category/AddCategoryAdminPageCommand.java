package com.newsstand.command.admin.category;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page used to add category.
 */
public class AddCategoryAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AddCategoryAdminPageCommand.class);

    private static String addCategoryPage;
    private static String loginPage;

    public AddCategoryAdminPageCommand(){
        LOGGER.info("Initializing AddCategoryAdminPageCommand");

        MappingProperties properties = MappingProperties.getInstance();
        addCategoryPage = properties.getProperty("adminAddCategoryPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = addCategoryPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }

        return resultPage;
    }
}
