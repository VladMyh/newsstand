package com.newsstand.model.magazine;

import java.io.Serializable;

public class Magazine implements Serializable{
    private long id;
    private String title;
    private Publisher publisher;
    private Category category;
    private Float price;
    private Long quantity;
    private String description;

    public Magazine() {}

    public Magazine(long id, String title, Publisher publisher, Category category,
                    Float price, Long quantity, String description) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (id != magazine.id) return false;
        if (!title.equals(magazine.title)) return false;
        if (!publisher.equals(magazine.publisher)) return false;
        if (!category.equals(magazine.category)) return false;
        if (!price.equals(magazine.price)) return false;
        if (!quantity.equals(magazine.quantity)) return false;
        return description != null ? description.equals(magazine.description) : magazine.description == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
