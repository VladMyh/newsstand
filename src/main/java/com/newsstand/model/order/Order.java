package com.newsstand.model.order;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.User;

import java.util.Date;
import java.util.List;

public class Order {
    private long id;
    private User user;
    private Date date;
    private List<Magazine> items;
    private Float totalCost;
    private boolean completed;

    public Order() {}

    public Order(long id, User user, Date date, List<Magazine> items, Float totalCost, boolean completed) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.items = items;
        this.totalCost = totalCost;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Magazine> getItems() {
        return items;
    }

    public void setItems(List<Magazine> items) {
        this.items = items;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (completed != order.completed) return false;
        if (!user.equals(order.user)) return false;
        if (!date.equals(order.date)) return false;
        if (!items.equals(order.items)) return false;
        return totalCost.equals(order.totalCost);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + user.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + items.hashCode();
        result = 31 * result + totalCost.hashCode();
        result = 31 * result + (completed ? 1 : 0);
        return result;
    }
}
