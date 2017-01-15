package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static String mainPage;

    public LogoutController(){
        LOGGER.info("Initializing LogoutController");

        MappingProperties properties = MappingProperties.getInstance();
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        LOGGER.info("Logging out user " + request.getSession().getAttribute("email"));

        request.getSession().invalidate();

        return mainPage;
    }
}
