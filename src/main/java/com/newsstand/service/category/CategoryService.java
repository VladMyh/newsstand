package com.newsstand.service.category;

import com.newsstand.model.magazine.Category;

import java.util.List;

public interface CategoryService {
    /**
     * This method gets all categories.
     *
     * @return A list of all categories.
     */
    List<Category> findAll();

    /**
     * This method gets category by id.
     *
     * @param id Id of the category to find.
     * @return   Category object.
     */
    Category findCategoryById(Long id);

    /**
     * This method creates new category.
     *
     * @param category Category object to be created.
     * @return         Updated object.
     */
    Category createCategory(Category category);

    /**
     * This method deletes category.
     *
     * @param id Id of the category to delete.
     * @return   True if category deleted successfully, otherwise false.
     */
    boolean deleteCategoryById(Long id);

    /**
     * This method updates category.
     *
     * @param category Object to be updated.
     * @return         An updated object.
     */
    Category updateCategory(Category category);
}
