package com.newsstand.command.admin.publisher;


import com.newsstand.command.ServletCommand;
import com.newsstand.dao.publisher.MysqlPublisherDaoImpl;
import com.newsstand.model.magazine.Publisher;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle POST requests to create new publisher.
 */
public class AddPublisgerAdminCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(AddPublisgerAdminCommand.class);

	private static PublisherService publisherService;

	private static String addPublisherPage;

	public AddPublisgerAdminCommand(){
		LOGGER.info("Initializing AddPublisgerAdminCommand");

		publisherService = new PublisherServiceImpl(MysqlPublisherDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		addPublisherPage = properties.getProperty("adminAddPublisherPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = addPublisherPage;

		if(request.getParameter("title") != null) {
			Publisher publisher = new Publisher();
			publisher.setTitle(request.getParameter("title"));

			publisher = publisherService.createPublisher(publisher);

			request.setAttribute("success", publisher != null);
		}

		return resultPage;
	}
}
