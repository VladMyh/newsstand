package com.newsstand.model.magazine;

public class MagazineBuilder {
	private Long id;
	private String title;
	private Publisher publisher;
	private Category category;
	private Float price;
	private String description;
	private Long imageId;
	private Boolean enabled;

	public MagazineBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public MagazineBuilder setTitle(String title) {
		this.title = title;
		return this;
	}

	public MagazineBuilder setPublisher(Publisher publisher) {
		this.publisher = publisher;
		return this;
	}

	public MagazineBuilder setCategory(Category category) {
		this.category = category;
		return this;
	}

	public MagazineBuilder setPrice(Float price) {
		this.price = price;
		return this;
	}

	public MagazineBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public MagazineBuilder setImageId(Long imageId) {
		this.imageId = imageId;
		return this;
	}

	public MagazineBuilder setEnabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public Magazine build() {
		return new Magazine(id, title, publisher, category, price, description, imageId, enabled);
	}
}
