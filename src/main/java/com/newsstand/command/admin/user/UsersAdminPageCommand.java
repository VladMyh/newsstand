package com.newsstand.command.admin.user;

import com.newsstand.command.ServletCommand;
import com.newsstand.model.user.User;
import com.newsstand.model.user.UserType;
import com.newsstand.properties.MappingProperties;
import com.newsstand.service.user.UserService;
import com.newsstand.service.user.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class is used to GET requests to the admin page to view users.
 */
public class UsersAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = Logger.getLogger(UsersAdminPageCommand.class);

    private static UserService userService;

    private static String page;

    public UsersAdminPageCommand(){
        LOGGER.info("Initializing UsersAdminPageCommand");

        userService = UserServiceImpl.getInstance();

        MappingProperties properties = MappingProperties.getInstance();
        page = properties.getProperty("adminUsersPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        try {
            Long pageNum = Long.parseLong(request.getParameter("p"));
            Long size = Long.parseLong(request.getParameter("s"));

            List<User> page = userService.getPageByUserType(pageNum, size, UserType.USER);

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
