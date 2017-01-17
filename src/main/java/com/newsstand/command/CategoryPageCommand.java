package com.newsstand.command;

import com.newsstand.model.magazine.Category;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


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

            Long categoryId = Long.parseLong(request.getParameter("catId"));
            Long pageNum = Long.parseLong(request.getParameter("p"));
            Long size = Long.parseLong(request.getParameter("s"));

            Category category = categoryService.findCategoryById(categoryId);
            List<Magazine> page = magazineService.getPageByCategoryId(pageNum, size, category.getId());

            request.setAttribute("categories", categoryService.getAllCategories());
            request.setAttribute("page", page);
            request.setAttribute("category", category);
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("pageSize", size);
            request.setAttribute("currSize", page.size());

            resultPage = categoryPage;
        }

        return resultPage;
    }
}
