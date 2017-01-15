package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private static String page;

    public RegisterCommand(){
        LOGGER.info("Initializing LoginCommand");

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("registerPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        return page;
    }
}
