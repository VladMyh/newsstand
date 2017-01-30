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

	/**
	 * This method finds subscription type by id.
	 *
	 * @param id Id of the subscription type to find.
	 * @return   Subscription type object found, otherwise null.
	 */
	SubscriptionType findSubscriptionTypeById(Long id);
}
