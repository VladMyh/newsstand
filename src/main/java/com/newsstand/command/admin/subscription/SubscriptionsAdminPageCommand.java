package com.newsstand.command.admin.subscription;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
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
			Long pageNum = Long.parseLong(request.getParameter("p"));
			Long size = Long.parseLong(request.getParameter("s"));

			List<Subscription> page = subscriptionService.getPage(pageNum, size);
			request.setAttribute("page", page);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", size);
			request.setAttribute("currSize", page.size());
		}
		catch (NumberFormatException ex) {
			LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
					+ request.getParameter("s") +" to long");
		}

		return page;
	}
}
