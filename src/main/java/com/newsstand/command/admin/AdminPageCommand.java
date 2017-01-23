package com.newsstand.command.admin;

import com.newsstand.command.ServletCommand;
import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(AdminPageCommand.class);

    private static String page;

    public AdminPageCommand(){
        LOGGER.info("Initializing AdminPageCommand");

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminDashboardPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        return page;
    }
}
