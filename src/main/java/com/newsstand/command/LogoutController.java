package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle user logout, by invalidating session.
 */
public class LogoutController implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String mainPage;

    public LogoutController(){
        LOGGER.info("Initializing LogoutController");

        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        LOGGER.info("Logging out user " + request.getSession().getAttribute("email"));

        request.getSession().invalidate();

        request.setAttribute("categories", categoryService.getAllCategories());
        request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

        return mainPage;
    }
}
