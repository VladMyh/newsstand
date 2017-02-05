package com.newsstand.command;

import com.newsstand.dao.category.MysqlCategoryDaoImpl;
import com.newsstand.dao.image.MysqlImageDaoImpl;
import com.newsstand.dao.magazine.MysqlMagazineDaoImpl;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the main page.
 */
public class GetMainPageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(GetMainPageCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String page;

    public GetMainPageCommand(){
        LOGGER.info("Initializing GetMainPageCommand");

        categoryService = new CategoryServiceImpl(MysqlCategoryDaoImpl.getInstance());
        magazineService = new MagazineServiceImpl(MysqlMagazineDaoImpl.getInstance(),
                                                  MysqlImageDaoImpl.getInstance());

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

        if(request.getParameter("locale") != null) {
            String locale = request.getParameter("locale");
            switch (locale) {
                case "en":
                    request.getSession().setAttribute("locale", "en");
                    break;
                case "ru":
                    request.getSession().setAttribute("locale", "ru");
                    break;
            }
        }

        return page;
    }
}
