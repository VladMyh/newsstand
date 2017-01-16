package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(MainPageCommand.class);

    private static CategoryService categoryService;

    private static String page;

    public MainPageCommand(){
        LOGGER.info("Initializing MainPageCommand");

        categoryService = CategoryServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        request.setAttribute("categories", categoryService.getAllCategories());

        return page;
    }
}
