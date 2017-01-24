package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MagazinesAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(MagazinesAdminPageCommand.class);

    private static MagazineService magazineService;

    private static String page;

    public MagazinesAdminPageCommand(){
        LOGGER.info("Initializing MagazinesAdminPageCommand");

        magazineService = MagazineServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminMagazinesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        return page;
    }
}
