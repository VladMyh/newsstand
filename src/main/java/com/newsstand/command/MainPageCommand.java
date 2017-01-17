package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(MainPageCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String page;

    public MainPageCommand(){
        LOGGER.info("Initializing MainPageCommand");

        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        request.setAttribute("categories", categoryService.getAllCategories());
        request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

        return page;
    }
}
