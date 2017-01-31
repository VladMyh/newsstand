package com.newsstand.service.subscription;

import com.newsstand.dao.subscription.MysqlSubscriptionDaoImpl;
import com.newsstand.dao.subscription.SubscriptionDao;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.model.user.User;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

	private static final Logger LOGGER = Logger.getLogger(SubscriptionServiceImpl.class);

	private static SubscriptionServiceImpl INSTANCE;
	private static SubscriptionDao subscriptionDao;

	private SubscriptionServiceImpl() {
		LOGGER.info("Initializing SubscriptionServiceImpl");

		subscriptionDao = MysqlSubscriptionDaoImpl.getInstance();
	}

	public static SubscriptionServiceImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new SubscriptionServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public Subscription createSubscription(Subscription subscription) {
		LOGGER.info("Creating new subscription");

		return subscriptionDao.createSubscription(subscription);
	}

	@Override
	public boolean checkIfUserSubscribed(User user, Magazine magazine) {
		LOGGER.info("Checking if user is subscribed to magazine");

		return subscriptionDao.checkIfUserSubscribed(user.getId(), magazine.getId());
	}

	@Override
	public List<Subscription> getUserSubscriptions(Long userId) {
		LOGGER.info("Finding subscription by user id " + userId);

		return subscriptionDao.findSubscriptionsByUserId(userId);
	}

}
