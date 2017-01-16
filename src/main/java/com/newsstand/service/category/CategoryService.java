package com.newsstand.service.category;

import java.util.List;

public interface CategoryService {
    /**
     * This method gets all categories.
     *
     * @return A list of all categories.
     */
    List<com.newsstand.model.magazine.Category> getAllCategories();
}
