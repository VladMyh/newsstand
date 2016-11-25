package com.newsstand.model.magazine;

import java.util.List;

public class Magazine {
    private long id;
    private String title;
    private Publisher publisher;
    private List<Tag> tags;
    private Float price;
    private Long quantity;

    public Magazine() {}

    public Magazine(long id, String title, Publisher publisher, List<Tag> tags, Float price, Long quantity) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.tags = tags;
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
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
        if (!publisher.equals(magazine.publisher)) return false;
        if (!tags.equals(magazine.tags)) return false;
        if (!price.equals(magazine.price)) return false;
        return quantity.equals(magazine.quantity);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        return result;
    }
}
