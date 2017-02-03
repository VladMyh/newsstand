package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Category;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.magazine.Publisher;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.image.ImageService;
import com.newsstand.service.image.ImageServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.publisher.PublisherService;
import com.newsstand.service.publisher.PublisherServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * This class is used to handle GET requests to the page admin used to add new magazines,
 * and POST requests to add new magazines.
 */
public class AddMagazineAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AddMagazineAdminPageCommand.class);

    private static MagazineService magazineService;
    private static PublisherService publisherService;
    private static CategoryService categoryService;
    private static ImageService imageService;

    private static String addMagazinePage;
    private static String loginPage;

    public AddMagazineAdminPageCommand(){
        LOGGER.info("Initializing AddMagazineAdminPageCommand");

        magazineService = MagazineServiceImpl.getInstance();
        publisherService = PublisherServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
        imageService = ImageServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        addMagazinePage = properties.getProperty("adminAddMagazinePage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = addMagazinePage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true) &&
            !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }
        if(request.getParameter("title") != null && request.getParameter("quantity") != null &&
            request.getParameter("price") != null && request.getParameter("publisher") != null &&
            request.getParameter("category") != null && request.getParameter("description") != null) {
            try {
                Part filePart = request.getPart("image");
                Long imageId = null;

                if(filePart != null && !Paths.get(filePart.getSubmittedFileName()).getFileName().toString().isEmpty()) {
                    InputStream image = filePart.getInputStream();
                    imageId = imageService.createImage(image);
                }

                Category category = new Category();
                category.setId(Long.parseLong(request.getParameter("category")));

                Publisher publisher = new Publisher();
                publisher.setId(Long.parseLong(request.getParameter("publisher")));

                Magazine magazine = new Magazine();
                magazine.setTitle(request.getParameter("title"));
                magazine.setDescription(request.getParameter("description"));
                magazine.setPrice(Float.parseFloat(request.getParameter("price")));
                magazine.setQuantity(Long.parseLong(request.getParameter("quantity")));
                magazine.setCategory(category);
                magazine.setPublisher(publisher);
                magazine.setImageId(imageId);

                magazineService.createMagazine(magazine);

            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id")
                                              + ", "
                                              + request.getParameter("category")
                                              + ", "
                                              + request.getParameter("publisher")
                                              + " to long");
            } catch (ServletException | IOException e) {
                LOGGER.info(e.getMessage());
            }
        }
        else {
            request.setAttribute("publishers", publisherService.findAll());
            request.setAttribute("categories", categoryService.findAll());
        }

        return resultPage;
    }
}
