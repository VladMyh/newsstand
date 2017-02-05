package com.newsstand.command.admin.user;

import com.newsstand.command.ServletCommand;
import com.newsstand.dao.subscription.MysqlSubscriptionDaoImpl;
import com.newsstand.dao.user.MysqlUserDaoImpl;
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

public class GetUserInfoAdminCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(GetUserInfoAdminCommand.class);

	private static UserService userService;
	private static SubscriptionService subscriptionService;

	private static String usersPage;
	private static String userInfoPage;

	public GetUserInfoAdminCommand(){
		LOGGER.info("Initializing GetUserInfoAdminCommand");

		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());
		subscriptionService = new SubscriptionServiceImpl(MysqlSubscriptionDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		usersPage = properties.getProperty("adminUsersPage");
		userInfoPage = properties.getProperty("adminUserInfoPage");
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = usersPage;

		if(request.getParameter("id") != null) {
			try {
				Long id = Long.parseLong(request.getParameter("id"));
				User user = userService.findUserById(id);

				if(user != null) {
					List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(user.getId());

					request.setAttribute("user", user);
					request.setAttribute("isSubscriptionsEmpty", subscriptions.isEmpty());
					request.setAttribute("subscriptions", subscriptions);
					resultPage = userInfoPage;
				}
			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse id " + request.getParameter("p") + " to long");
			}
		}

		return resultPage;
	}
}
