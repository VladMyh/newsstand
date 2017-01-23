package com.newsstand.command;

import com.newsstand.dto.UserDto;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class LoginCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static UserService userService;

    private static String loginPage;
    private static String mainPage;
    private static String adminPage;

    public LoginCommand(){
        LOGGER.info("Initializing LoginCommand");

        userService = UserServiceImpl.getInstance();

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
            UserDto userDto = userService.getUserByCredentials(request.getParameter("email"),
                                                               request.getParameter("password"));

            if (userDto != null) {
                HttpSession session = request.getSession();
                session.setAttribute("email", userDto.getEmail());
                session.setAttribute("username", userDto.getFirstName() + " " + userDto.getLastName());
                session.setAttribute("authenticated", true);
                session.setAttribute("role", userDto.getUserType());

                if(Objects.equals(userDto.getUserType(), UserType.ADMIN.name())) {
                    resultPage = adminPage;
                }
                else {
                    resultPage = mainPage;
                }
            }
        }

        return resultPage;
    }
}
