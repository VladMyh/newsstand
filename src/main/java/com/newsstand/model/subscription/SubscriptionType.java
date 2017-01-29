package com.newsstand.model.subscription;

public class SubscriptionType{
    private Long id;
    private String name;
    private Float priceMultiplier;

    public SubscriptionType() {}

    public SubscriptionType(Long id, String name, Float priceMultiplier) {
        this.id = id;
        this.name = name;
        this.priceMultiplier = priceMultiplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(Float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionType that = (SubscriptionType) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return priceMultiplier.equals(that.priceMultiplier);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + priceMultiplier.hashCode();
        return result;
    }
}
