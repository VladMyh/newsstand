package com.newsstand.command;

import com.newsstand.model.magazine.Category;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle GET requests to the page to view all the magazines
 * of the particular category.
 */
public class CategoryPageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(CategoryPageCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String categoryPage;
    private static String errorPage;

    public CategoryPageCommand(){
        LOGGER.info("Initializing CategoryPageCommand");

        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        categoryPage = properties.getProperty("categoryPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = errorPage;

        if(request.getParameter("catId") != null && request.getParameter("p") != null &&
            request.getParameter("s") != null) {

            try {
                Long categoryId = Long.parseLong(request.getParameter("catId"));
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                Category category = categoryService.findCategoryById(categoryId);
                Page<Magazine> page = magazineService.getPageByCategoryId(pageNum, size, category.getId());

                request.setAttribute("categories", categoryService.findAll());
                request.setAttribute("page", page);
                request.setAttribute("category", category);

                resultPage = categoryPage;
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("catId") + ", "
                                              + request.getParameter("p") + ", "
                                              + request.getParameter("s")+ " to long");
            }
        }

        return resultPage;
    }
}
