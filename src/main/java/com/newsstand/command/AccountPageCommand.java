package com.newsstand.command;

import com.newsstand.model.subscription.Subscription;
import com.newsstand.model.user.User;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle GET requests to the user account page.
 */
public class AccountPageCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetLoginPageCommand.class);

	private static UserService userService;
	private static SubscriptionService subscriptionService;

	private static String mainPage;
	private static String accountPage;

	public AccountPageCommand(){
		LOGGER.info("Initializing AccountPageCommand");

		userService = UserServiceImpl.getInstance();
		subscriptionService = SubscriptionServiceImpl.getInstance();

		MappingProperties properties = MappingProperties.getInstance();
		mainPage = properties.getProperty("mainPage");
		accountPage = properties.getProperty("accountPage");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = mainPage;

		if(request.getSession().getAttribute("authenticated") != null &&
		   request.getSession().getAttribute("authenticated").equals(true)) {

			User user = userService.findUserByEmail(request.getSession().getAttribute("email").toString());
			List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(user.getId());

			request.setAttribute("user", user);
			request.setAttribute("isSubscriptionsEmpty", subscriptions.isEmpty());
			request.setAttribute("subscriptions", subscriptions);

			resultPage = accountPage;
		}

		return resultPage;
	}
}
