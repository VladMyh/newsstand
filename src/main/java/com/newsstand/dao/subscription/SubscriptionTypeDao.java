package com.newsstand.dao.subscription;

import com.newsstand.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeDao {
    /**
     * This method creates new subscription type.
     *
     * @param subscriptionType Object to be created.
     * @return                 Created object.
     */
    SubscriptionType createSubscriptionType(SubscriptionType subscriptionType);

    /**
     * This method updates subscription type.
     *
     * @param subscriptionType Object to be updated.
     * @return                 Undated object.
     */
    SubscriptionType updateSubscriptionType(SubscriptionType subscriptionType);

    /**
     * This method deletes subscription type by id.
     *
     * @param id Id of sunscription type to delete.
     */
    void deleteSubscriptionTypeById(Long id);

    /**
     * This method finds subscription type by id.
     *
     * @param id Id of object to find.
     * @return   Object found by id, otherwise null.
     */
    SubscriptionType findSubscriptionTypeById(Long id);

    /**
     * This method finds all subscription types.
     *
     * @return A list of subscription types.
     */
    List<SubscriptionType> findAll();
}
