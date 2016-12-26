package com.newsstand.model.subscription;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.User;

import java.io.Serializable;
import java.util.Date;

public class Subscription implements Serializable{
    private Long id;
    private User user;
    private Magazine magazine;
    private SubscriptionType type;
    private Date startDate;
    private Date endDate;
    private Float Price;

    public Subscription() {}

    public Subscription(Long id, User user, Magazine magazine, SubscriptionType type, Date startDate, Date endDate, Float price) {
        this.id = id;
        this.user = user;
        this.magazine = magazine;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        Price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (!id.equals(that.id)) return false;
        if (!user.equals(that.user)) return false;
        if (!magazine.equals(that.magazine)) return false;
        if (type != that.type) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        return Price.equals(that.Price);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + magazine.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + Price.hashCode();
        return result;
    }
}
