package com.newsstand.command.admin.publisher;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Publisher;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the page to add new publisher,
 * and POST requests to create new publisher
 */
public class AddPublisherAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AddPublisherAdminPageCommand.class);

    private static PublisherService publisherService;

    private static String addPublisherPage;
    private static String loginPage;

    public AddPublisherAdminPageCommand(){
        LOGGER.info("Initializing AddPublisherAdminPageCommand");

        publisherService = PublisherServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        addPublisherPage = properties.getProperty("adminAddPublisherPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = addPublisherPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else if(request.getParameter("title") != null) {
            Publisher publisher = new Publisher();
            publisher.setTitle(request.getParameter("title"));

            publisher = publisherService.createPublisher(publisher);

            request.setAttribute("success", publisher != null);
        }

        return resultPage;
    }
}
