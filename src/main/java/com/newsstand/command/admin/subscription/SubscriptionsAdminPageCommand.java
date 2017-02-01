package com.newsstand.command.admin.subscription;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle GET requests to the admin page used to display subscriptions.
 */
public class SubscriptionsAdminPageCommand implements ServletCommand {
	private static final Logger LOGGER = Logger.getLogger(SubscriptionsAdminPageCommand.class);

	private static SubscriptionService subscriptionService;

	private static String page;

	public SubscriptionsAdminPageCommand(){
		LOGGER.info("Initializing SubscriptionsAdminPageCommand");

		subscriptionService = SubscriptionServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		page = properties.getProperty("adminSubscriptionPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");

		try {
			Integer pageNum = Integer.parseInt(request.getParameter("p"));
			Integer size = Integer.parseInt(request.getParameter("s"));

			List<Subscription> items = subscriptionService.getPage(pageNum, size);
			Page<Subscription> page = new Page<>(items, pageNum, size);

			request.setAttribute("page", page);
		}
		catch (NumberFormatException ex) {
			LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
					+ request.getParameter("s") +" to long");
		}

		return page;
	}
}
