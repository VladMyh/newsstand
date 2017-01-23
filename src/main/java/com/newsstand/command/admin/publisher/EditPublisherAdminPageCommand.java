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

public class EditPublisherAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(EditPublisherAdminPageCommand.class);

    private static PublisherService publisherService;

    private static String editPublisherPage;
    private static String publishersPage;
    private static String loginPage;

    public EditPublisherAdminPageCommand(){
        LOGGER.info("Initializing EditPublisherAdminPageCommand");

        publisherService = PublisherServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        editPublisherPage = properties.getProperty("adminEditPublisherPage");
        publishersPage = properties.getProperty("adminPublishersPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = editPublisherPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        else if(request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Publisher publisher = publisherService.findPublisherById(id);

                if(publisher != null) {
                    request.setAttribute("publisher", publisher);
                }
                else {
                    resultPage = publishersPage;
                }
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }

        }

        return resultPage;
    }
}
