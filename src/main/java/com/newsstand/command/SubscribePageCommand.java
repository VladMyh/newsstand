package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribePageCommand implements ServletCommand {

    private static final Logger LOGGER = Logger.getLogger(SubscribePageCommand.class);

    private static MagazineService magazineService;

    private static String subscribePage;

    public SubscribePageCommand(){
        LOGGER.info("Initializing SubscribePageCommand");

        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        subscribePage = properties.getProperty("subscribePage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = subscribePage;



        return resultPage;
    }
}
