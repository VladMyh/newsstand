package com.newsstand.command;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MagazinePageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(MagazinePageCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String magazinePage;
    private static String errorPage;

    public MagazinePageCommand(){
        LOGGER.info("Initializing MainPageCommand");

        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        magazinePage = properties.getProperty("magazinePage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = errorPage;

        if(request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Magazine magazine = magazineService.findMagazineById(id);

                if(magazine != null) {
                    request.setAttribute("categories", categoryService.getAllCategories());
                    request.setAttribute("magazine", magazine);

                    resultPage = magazinePage;
                }
                else {
                    LOGGER.info("Magazine with id " + id + " doesn't exist");
                }
            }
            catch (NumberFormatException ex) {
                LOGGER.error("Invalid get parameter " + request.getParameter("id"));
                LOGGER.error(ex.getMessage());
            }
        }

        return resultPage;
    }
}
