package com.newsstand.dao.subscription;

import com.newsstand.model.subscription.Subscription;

public interface SubscriptionDao {
    /**
     * This method creates new subscription.
     *
     * @param subscription Object to be created.
     * @return             Created object.
     */
    Subscription createSubscription(Subscription subscription);

    /**
     * This method updated subscription.
     *
     * @param subscription Object to be updated.
     * @return             Updated object.
     */
    Subscription updateSubscription(Subscription subscription);

    /**
     * This method deletes subscription by id.
     *
     * @param id Id of subscription to be deleted.
     */
    void deleteSubscriptionById(Long id);

    /**
     * This method finds subscription by id.
     *
     * @param id Id of subscription to find.
     * @return   Object found by id.
     */
    Subscription findSubscriptionById(Long id);

    /**
     * This method returns weather user is currently subscribed to magazine.
     *
     * @param userId     Id of the user.
     * @param magazineId Id of the magazine.
     * @return           True if user currently subscribed, otherwise false.
     */
    boolean checkIfUserSubscribed(Long userId, Long magazineId);
}
