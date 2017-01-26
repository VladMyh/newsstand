package com.newsstand.command;

import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.category.CategoryService;
import com.newsstand.service.category.CategoryServiceImpl;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * This class is used to handle GET requests to the login page,
 * and POST requests to authenticate user.
 */
public class LoginCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static UserService userService;
    private static CategoryService categoryService;
    private static MagazineService magazineService;

    private static String loginPage;
    private static String mainPage;
    private static String adminPage;

    public LoginCommand(){
        LOGGER.info("Initializing LoginCommand");

        userService = UserServiceImpl.getInstance();
        categoryService = CategoryServiceImpl.getInstance();
        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPage");
        mainPage = properties.getProperty("mainPage");
        adminPage = properties.getProperty("adminDashboardPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = loginPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        }
        else if(request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning login page");
            return resultPage;
        }
        else {
            User user = userService.getUserByCredentials(request.getParameter("email"),
                                                         request.getParameter("password"));

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("email", user.getEmail());
                session.setAttribute("username", user.getFirstName() + " " + user.getLastName());
                session.setAttribute("authenticated", true);
                session.setAttribute("role", user.getUserType().name());

                if(Objects.equals(user.getUserType(), UserType.ADMIN)) {
                    resultPage = adminPage;
                }
                else {
                    request.setAttribute("categories", categoryService.getAllCategories());
                    request.setAttribute("latestMagazines", magazineService.findLatestAdded(6));

                    resultPage = mainPage;
                }
            }
        }

        return resultPage;
    }
}
