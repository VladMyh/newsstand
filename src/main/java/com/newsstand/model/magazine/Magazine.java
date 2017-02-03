package com.newsstand.model.magazine;

import java.io.Serializable;

public class Magazine implements Serializable{
    private Long id;
    private String title;
    private Publisher publisher;
    private Category category;
    private Float price;
    private Long quantity;
    private String description;
    private Long imageId;

    public Magazine() {}

    public Magazine(Long id, String title, Publisher publisher, Category category,
                    Float price, Long quantity, String description, Long imageId) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageId = imageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Magazine magazine = (Magazine) o;

        if (!id.equals(magazine.id)) return false;
        if (!title.equals(magazine.title)) return false;
        if (!publisher.equals(magazine.publisher)) return false;
        if (!category.equals(magazine.category)) return false;
        if (!price.equals(magazine.price)) return false;
        if (!quantity.equals(magazine.quantity)) return false;
        if (description != null ? !description.equals(magazine.description) : magazine.description != null)
            return false;
        return imageId != null ? imageId.equals(magazine.imageId) : magazine.imageId == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + quantity.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (imageId != null ? imageId.hashCode() : 0);
        return result;
    }
}
