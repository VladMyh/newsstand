package com.newsstand.model.magazine;

import java.io.Serializable;

public class Magazine implements Serializable{
    private long id;
    private String title;
    private Publisher publisher;
    private Category category;
    private Float price;
    private Long quantity;

    public Magazine() {}

    public Magazine(long id, String title, Publisher publisher, Category category, Float price, Long quantity) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (id != magazine.id) return false;
        if (!title.equals(magazine.title)) return false;
        if (publisher != null ? !publisher.equals(magazine.publisher) : magazine.publisher != null) return false;
        if (category != null ? !category.equals(magazine.category) : magazine.category != null) return false;
        if (price != null ? !price.equals(magazine.price) : magazine.price != null) return false;
        return quantity != null ? quantity.equals(magazine.quantity) : magazine.quantity == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
