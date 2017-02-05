package com.newsstand.service.subscription;

import com.newsstand.dao.subscription.MysqlSubscriptionTypeDao;
import com.newsstand.dao.subscription.SubscriptionDao;
import com.newsstand.dao.subscription.SubscriptionTypeDao;
import com.newsstand.model.subscription.SubscriptionType;
import org.apache.log4j.Logger;

import java.util.List;

public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

	private static final Logger LOGGER = Logger.getLogger(SubscriptionTypeServiceImpl.class);

	private SubscriptionTypeDao subscriptionTypeDao;

	public SubscriptionTypeServiceImpl(SubscriptionTypeDao subscriptionDao) {
		LOGGER.info("Initializing SubscriptionTypeServiceImpl");

		this.subscriptionTypeDao = subscriptionDao;
	}

	@Override
	public List<SubscriptionType> findAll() {
		LOGGER.info("Getting all subscription types");

		return subscriptionTypeDao.findAll();
	}

	@Override
	public SubscriptionType findSubscriptionTypeById(Long id) {
		LOGGER.info("Finding subscription type by id " + id);

		if(id == null) {
			return null;
		}

		return subscriptionTypeDao.findSubscriptionTypeById(id);
	}
}
