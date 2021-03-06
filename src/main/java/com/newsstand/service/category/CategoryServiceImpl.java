package com.newsstand.service.category;

import com.newsstand.dao.category.CategoryDao;
import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.model.magazine.Category;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    private  CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        LOGGER.info("Initializing CategoryServiceImpl");

        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        LOGGER.info("Getting all categories");

        return categoryDao.findAll();
    }

    @Override
    public Category findCategoryById(Long id) {
        LOGGER.info("Finding category by id " + id);

        if(id == null) {
            return null;
        }

        return categoryDao.findCategoryById(id);
    }

    @Override
    public Category createCategory(Category category) {
        LOGGER.info("Creating new category");

        if(categoryDao.findCategoryByName(category.getName()) == null) {
            return categoryDao.createCategory(category);
        }

        return null;
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        LOGGER.info("Deleting category with id " + id);

        if(id == null) {
            return false;
        }

        return categoryDao.deleteCategoryById(id);
    }

    @Override
    public Category updateCategory(Category category) {
        LOGGER.info("Updating category with id " + category.getId());

        if(category == null) {
            return null;
        }

        return categoryDao.updateCategory(category);
    }
}
