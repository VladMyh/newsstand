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
 * This class is used to handle POST requests to update publishers.
 */
public class UpdatePublisherAdminCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UpdatePublisherAdminCommand.class);

    private static PublisherService publisherService;

    private static String publishersPage;
    private static String loginPage;

    public UpdatePublisherAdminCommand(){
        LOGGER.info("Initializing UpdatePublisherAdminCommand");

        publisherService = PublisherServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        publishersPage = properties.getProperty("adminPublishersPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = publishersPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else if(request.getParameter("id") != null && request.getParameter("title") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Publisher publisher = new Publisher();
                publisher.setId(id);
                publisher.setTitle(request.getParameter("title"));
                publisherService.updatePublisher(publisher);

                request.setAttribute("updateSuccess", true);
                request.setAttribute("publishers", publisherService.getAllPublishers());
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }

        }

        return resultPage;
    }
}
