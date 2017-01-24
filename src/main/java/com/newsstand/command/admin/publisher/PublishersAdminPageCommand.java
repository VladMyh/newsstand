package com.newsstand.command.admin.publisher;

import com.newsstand.command.ServletCommand;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page to view all publishers.
 */
public class PublishersAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(PublishersAdminPageCommand.class);

    private static PublisherService publisherService;

    private static String page;

    public PublishersAdminPageCommand(){
        LOGGER.info("Initializing PublishersAdminPageCommand");

        publisherService = PublisherServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminPublishersPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        request.setAttribute("publishers", publisherService.getAllPublishers());

        return page;
    }
}
