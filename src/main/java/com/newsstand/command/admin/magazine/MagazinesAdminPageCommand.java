package com.newsstand.command.admin.magazine;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.magazine.Magazine;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.magazine.MagazineService;
import com.newsstand.service.magazine.MagazineServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
            Long pageNum = Long.parseLong(request.getParameter("p"));
            Long size = Long.parseLong(request.getParameter("s"));

            List<Magazine> page = magazineService.getPage(pageNum, size);

            request.setAttribute("page", page);
            request.setAttribute("pageNum", pageNum);
            request.setAttribute("pageSize", size);
            request.setAttribute("currSize", page.size());
        }
        catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
                + request.getParameter("s") +" to long");
        }

        return page;
    }
}
