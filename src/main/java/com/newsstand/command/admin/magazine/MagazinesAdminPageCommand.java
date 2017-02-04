package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import com.newsstand.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to handle GET requests to the admin page used to display magazines.
 */
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

        try {
            Integer pageNum = Integer.parseInt(request.getParameter("p"));
            Integer size = Integer.parseInt(request.getParameter("s"));

            Page<Magazine> page = magazineService.getPage(pageNum, size);

            request.setAttribute("page", page);
        }
        catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
                + request.getParameter("s") +" to long");
        }

        return page;
    }
}
