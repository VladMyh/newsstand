package com.newsstand.service.subscription;

import com.newsstand.dao.subscription.MysqlSubscriptionTypeDao;
import com.newsstand.dao.subscription.SubscriptionTypeDao;
import com.newsstand.model.subscription.SubscriptionType;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

	private static final Logger LOGGER = Logger.getLogger(SubscriptionTypeServiceImpl.class);

	private static SubscriptionTypeServiceImpl INSTANCE;
	private static SubscriptionTypeDao subscriptionTypeDao;

	private SubscriptionTypeServiceImpl() {
		LOGGER.info("Initializing SubscriptionTypeServiceImpl");

		subscriptionTypeDao = MysqlSubscriptionTypeDao.getInstance();
	}

	public static SubscriptionTypeServiceImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new SubscriptionTypeServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public List<SubscriptionType> findAll() {
		LOGGER.info("Getting all subscription types");

		return subscriptionTypeDao.findAll();
	}
}
