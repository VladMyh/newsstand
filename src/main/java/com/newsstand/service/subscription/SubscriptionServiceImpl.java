package com.newsstand.service.subscription;

import com.newsstand.dao.subscription.MysqlSubscriptionDaoImpl;
import com.newsstand.dao.subscription.SubscriptionDao;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.model.user.User;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

	private static final Logger LOGGER = Logger.getLogger(SubscriptionServiceImpl.class);

	private  SubscriptionDao subscriptionDao;

	public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
		LOGGER.info("Initializing SubscriptionServiceImpl");

		this.subscriptionDao = subscriptionDao;
	}

	@Override
	public Subscription createSubscription(Subscription subscription) {
		LOGGER.info("Creating new subscription");

		if(subscription == null) {
			return null;
		}

		return subscriptionDao.createSubscription(subscription);
	}

	@Override
	public boolean checkIfUserSubscribed(User user, Magazine magazine) {
		LOGGER.info("Checking if user is subscribed to magazine");

		if(user == null || magazine == null) {
			return false;
		}

		return subscriptionDao.checkIfUserSubscribed(user.getId(), magazine.getId());
	}

	@Override
	public List<Subscription> getUserSubscriptions(Long userId) {
		LOGGER.info("Finding subscription by user id " + userId);

		if(userId == null) {
			return null;
		}

		return subscriptionDao.findSubscriptionsByUserId(userId);
	}

	@Override
	public Page<Subscription> getPage(Integer page, Integer size) {
		LOGGER.info("Getting page number " + page + ", of size " + size );

		if(page == null || size == null || page < 1 || size < 1) {
			return null;
		}

		List<Subscription> items = subscriptionDao.findPage((page - 1) * size, size);
		return new Page<>(items, page, size);
	}

}
