package com.newsstand.dao.category;

import com.newsstand.model.magazine.Category;

import java.util.List;

public interface CategoryDao {
    /**
     * This method saves new category to db.
     *
     * @param category Object to be created.
     * @return         An updated object.
     */
    Category createCategory(Category category);

    /**
     * This method updates category.
     *
     * @param category Object to be updated.
     * @return         An updated object.
     */
    Category updateCategory(Category category);

    /**
     * This method deletes category from db.
     *
     * @param id Id of the category to be deleted.
     * @return   True if deletion successful, otherwise false.
     */
    boolean deleteCategoryById(Long id);

    /**
     * This method finds category by id.
     *
     * @param id Id of the category.
     * @return   Category object retrieved from db, otherwise null.
     */
    Category findCategoryById(Long id);

    /**
     * This method finds category by name.
     *
     * @param name Name of the category.
     * @return     Category object with given name, otherwise null.
     */
    Category findCategoryByName(String name);

    /**
     * This method gets all categories
     *
     * @return A list of all categories.
     */
    List<Category> findAll();
}
