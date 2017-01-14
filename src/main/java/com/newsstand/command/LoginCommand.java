package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements ServletCommand{
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private static String url;

    public LoginCommand(){
        LOGGER.info("Initializing LoginCommand");

        MappingProperties properties = MappingProperties.getInstance();
        url = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        return url;
    }
}
