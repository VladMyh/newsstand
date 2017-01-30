package com.newsstand.model.subscription;

public class SubscriptionType{
    private Long id;
    private String name;
    private Float priceMultiplier;
    private Integer durationInMonth;

    public SubscriptionType() {}

    public SubscriptionType(Long id, String name, Float priceMultiplier, Integer durationInMonth) {
        this.id = id;
        this.name = name;
        this.priceMultiplier = priceMultiplier;
        this.durationInMonth = durationInMonth;
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

    public Integer getDurationInMonth() {
        return durationInMonth;
    }

    public void setDurationInMonth(Integer durationInMonth) {
        this.durationInMonth = durationInMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionType that = (SubscriptionType) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!priceMultiplier.equals(that.priceMultiplier)) return false;
        return durationInMonth.equals(that.durationInMonth);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + priceMultiplier.hashCode();
        result = 31 * result + durationInMonth.hashCode();
        return result;
    }
}
