package com.newsstand.service.subscription;

import com.newsstand.model.subscription.Subscription;

public interface SubscriptionService {

	/**
	 * This method creates new subscription.
	 *
	 * @param subscription Subscription object to be created.
	 * @return             Updated object.
	 */
	Subscription createSubscription(Subscription subscription);
}
