package com.newsstand.service.category;

import com.newsstand.dao.magazine.CategoryDao;
import com.newsstand.dao.magazine.MysqlCategoryDaoImpl;
import com.newsstand.model.magazine.Category;
import org.apache.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class);

    private static CategoryServiceImpl INSTANCE;
    private static CategoryDao categoryDao;

    private CategoryServiceImpl() {
        LOGGER.info("Initializing CategoryServiceImpl");

        categoryDao = MysqlCategoryDaoImpl.getInstance();
    }

    public static CategoryServiceImpl getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CategoryServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public List<Category> getAllCategories() {
        LOGGER.info("Getting all categories");

        return categoryDao.getAllCategories();
    }
}
