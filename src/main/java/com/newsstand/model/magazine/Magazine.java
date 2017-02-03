package com.newsstand.model.magazine;

import java.io.Serializable;

public class Magazine implements Serializable{
    private Long id;
    private String title;
    private Publisher publisher;
    private Category category;
    private Float price;
    private String description;
    private Long imageId;
    private Boolean enabled;

    public Magazine() {}

    public Magazine(Long id, String title, Publisher publisher, Category category,
                    Float price, String description, Long imageId, Boolean enabled) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.category = category;
        this.price = price;
        this.description = description;
        this.imageId = imageId;
        this.enabled = enabled;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
        if (!description.equals(magazine.description)) return false;
        if (!imageId.equals(magazine.imageId)) return false;
        return enabled.equals(magazine.enabled);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + imageId.hashCode();
        result = 31 * result + enabled.hashCode();
        return result;
    }
}
