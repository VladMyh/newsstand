package com.newsstand.service.subscription;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.subscription.Subscription;
import com.newsstand.model.user.User;

import java.util.List;

public interface SubscriptionService {

	/**
	 * This method creates new subscription.
	 *
	 * @param subscription Subscription object to be created.
	 * @return             Updated object.
	 */
	Subscription createSubscription(Subscription subscription);

	/**
	 * This method returns weather user is currently subscribed to magazine.
	 *
	 * @param user     User object to check.
	 * @param magazine Magazine object to check.
	 * @return         True if user currently subscribed, otherwise false.
	 */
	boolean checkIfUserSubscribed(User user, Magazine magazine);

	/**
	 * This method finds all subscriptions of user.
	 *
	 * @param userId Id of user.
	 * @return       List of user subscriptions.
	 */
	List<Subscription> getUserSubscriptions(Long userId);
}
