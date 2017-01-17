package com.newsstand.service.category;

import com.newsstand.model.magazine.Category;

import java.util.List;

public interface CategoryService {
    /**
     * This method gets all categories.
     *
     * @return A list of all categories.
     */
    List<Category> getAllCategories();

    /**
     * This method gets category by id.
     *
     * @param id Id of the category to find.
     * @return   Category object.
     */
    Category findCategoryById(Long id);
}
