package com.newsstand.command;

import com.newsstand.dto.UserDto;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private static UserService userService;

    private static String registerPage;
    private static String mainPage;

    public RegisterCommand(){
        LOGGER.info("Initializing RegisterCommand");

        userService = UserServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        registerPage = properties.getProperty("registerPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = registerPage;

        if(request.getSession().getAttribute("authenticated") != null &&
            request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        }
        else if(request.getParameter("fname") == null && request.getParameter("lname") == null &&
            request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning registration page");
            return resultPage;
        }
        else if(userService.checkEmailAvailability(request.getParameter("email"))) {
            LOGGER.info("New user registration");

            UserDto userDto = new UserDto();
            userDto.setFirstName(request.getParameter("fname"));
            userDto.setLastName(request.getParameter("lname"));
            userDto.setEmail(request.getParameter("email"));
            userDto.setPassword(request.getParameter("password"));

            if(userService.registerUser(userDto)) {
                resultPage = mainPage;
            }
        }

        return resultPage;
    }
}
