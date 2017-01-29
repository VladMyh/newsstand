package com.newsstand.service.subscription;

import com.newsstand.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

	/**
	 * This method returns all subscription types.
	 *
	 * @return A list of subscription types.
	 */
	List<SubscriptionType> findAll();
}
