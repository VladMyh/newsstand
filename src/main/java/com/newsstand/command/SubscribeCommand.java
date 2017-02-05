package com.newsstand.command;

import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.dao.subscription.MysqlSubscriptionDaoImpl;
import com.newsstand.dao.subscription.MysqlSubscriptionTypeDao;
import com.newsstand.dao.user.MysqlUserDaoImpl;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.model.subscription.SubscriptionType;
import com.newsstand.model.user.User;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
import com.newsstand.service.subscription.SubscriptionTypeService;
import com.newsstand.service.subscription.SubscriptionTypeServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Calendar;

public class SubscribeCommand implements ServletCommand {

	private static final Logger LOGGER = Logger.getLogger(SubscribeCommand.class);

	private static MagazineService magazineService;
	private static SubscriptionTypeService subscriptionTypeService;
	private static SubscriptionService subscriptionService;
	private static UserService userService;

	private static String mainPage;
	private static String subscriptionSuccessPage;

	public SubscribeCommand(){
		LOGGER.info("Initializing SubscribeCommand");

		magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
				                                  MysqlImageDaoImpl.getInstance());
		subscriptionTypeService = new SubscriptionTypeServiceImpl(MysqlSubscriptionTypeDao.getInstance());
		subscriptionService = new SubscriptionServiceImpl(MysqlSubscriptionDaoImpl.getInstance());
		userService = new UserServiceImpl(MysqlUserDaoImpl.getInstance());

		MappingProperties properties = MappingProperties.getInstance();
		mainPage = properties.getProperty("mainPage");
		subscriptionSuccessPage = properties.getProperty("subscriptionSuccessPage");
	}

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("Executing command");
		String resultPage = mainPage;

		if(request.getParameter("id") != null && request.getParameter("type") != null) {
			try {
				Long subTypeId = Long.parseLong(request.getParameter("type"));
				Long magazineId = Long.parseLong(request.getParameter("id"));

				SubscriptionType subscriptionType = subscriptionTypeService.findSubscriptionTypeById(subTypeId);
				Magazine magazine = magazineService.findMagazineById(magazineId);
				User user = userService.findUserByEmail(request.getSession().getAttribute("email").toString());

				if(magazine != null && magazine.getEnabled() && subscriptionType != null) {
					Subscription subscription = new Subscription();

					subscription.setMagazine(magazine);
					subscription.setType(subscriptionType);
					subscription.setPrice(magazine.getPrice() * subscriptionType.getPriceMultiplier());
					subscription.setUser(user);

					Date startDate = new Date(Calendar.getInstance().getTimeInMillis());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(startDate);
					calendar.add(Calendar.MONTH, subscriptionType.getDurationInMonth());
					Date endDate = new Date(calendar.getTimeInMillis());

					subscription.setStartDate(startDate);
					subscription.setEndDate(endDate);

					subscriptionService.createSubscription(subscription);

					resultPage = subscriptionSuccessPage;
				}
				else {
					LOGGER.info("Couldn't find magazine or subscriptionType by ids "
							+ magazine
							+ ", "
							+ subTypeId);
				}

			}
			catch (NumberFormatException ex) {
				LOGGER.info("Couldn't parse " + request.getParameter("id")
						+ ", "
						+ request.getParameter("type")
						+ " to long");
			}
		}

		return resultPage;
	}
}
