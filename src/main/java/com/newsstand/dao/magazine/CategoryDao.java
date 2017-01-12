package com.newsstand.dao.magazine;

import com.newsstand.model.magazine.Category;

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
     */
    void deleteCategoryById(Long id);

    /**
     * This method finds category by id.
     *
     * @param id Id of the category.
     * @return   Category object retrieved from db
     */
    Category findCategoryById(Long id);
}
