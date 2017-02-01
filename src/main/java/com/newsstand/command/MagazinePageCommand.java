package com.newsstand.command;

import com.newsstand.model.magazine.Magazine;
import com.newsstand.model.user.User;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.subscription.SubscriptionService;
import com.newsstand.service.subscription.SubscriptionServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the page used to view a single magazine.
 */
public class MagazinePageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(MagazinePageCommand.class);

    private static CategoryService categoryService;
    private static MagazineService magazineService;
    private static UserService userService;
    private static SubscriptionService subscriptionService;

    private static String magazinePage;
    private static String errorPage;

    public MagazinePageCommand(){
        LOGGER.info("Initializing MainPageCommand");

        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
        subscriptionService = SubscriptionServiceImpl.getInstance();

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

                if (magazine != null) {
                    if(request.getSession().getAttribute("authenticated") != null &&
                       request.getSession().getAttribute("authenticated").equals(true)) {
                        User user = userService.findUserByEmail(request.getSession().getAttribute("email").toString());
                        boolean isSubscribed = subscriptionService.checkIfUserSubscribed(user, magazine);

                        request.setAttribute("isSubscribed", isSubscribed);
                    }

                    request.setAttribute("categories", categoryService.findAll());
                    request.setAttribute("magazine", magazine);

                    resultPage = magazinePage;
                } else {
                    LOGGER.info("Magazine with id " + id + " doesn't exist");
                }
            }
            catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }
        }

        return resultPage;
    }
}
