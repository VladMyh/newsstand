package com.newsstand.command;

import com.newsstand.dto.UserDto;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static UserService userService;

    private static String loginPage;
    private static String mainPage;

    public LoginCommand(){
        LOGGER.info("Initializing LoginCommand");

        userService = UserServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        loginPage = properties.getProperty("loginPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = loginPage;

        if(request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning login page");
            return resultPage;
        }

        UserDto userDto = userService.getUserByCredentials(request.getParameter("email"),
                                                           request.getParameter("password"));

        if(userDto != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", userDto.getEmail());

            resultPage = mainPage;
        }

        return resultPage;
    }
}
