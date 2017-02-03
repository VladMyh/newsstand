package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page used to add new magazine.
 */
public class GetAddMagazineAdminPageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(GetAddMagazineAdminPageCommand.class);

    private static PublisherService publisherService;
    private static CategoryService categoryService;

    private static String addMagazinePage;
    private static String loginPage;

    public GetAddMagazineAdminPageCommand(){
        LOGGER.info("Initializing GetAddMagazineAdminPageCommand");

        publisherService = PublisherServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        addMagazinePage = properties.getProperty("adminAddMagazinePage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = addMagazinePage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else {
            request.setAttribute("publishers", publisherService.findAll());
            request.setAttribute("categories", categoryService.findAll());
        }

        return resultPage;
    }
}
