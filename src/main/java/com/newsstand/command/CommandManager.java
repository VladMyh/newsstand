package com.newsstand.command;

import com.newsstand.command.admin.AdminPageCommand;
import com.newsstand.command.admin.category.*;
import com.newsstand.command.admin.magazine.AddMagazineAdminPageCommand;
import com.newsstand.command.admin.magazine.MagazinesAdminPageCommand;
import com.newsstand.command.admin.publisher.*;
import com.newsstand.command.admin.user.AdminsAdminPageCommand;
import com.newsstand.command.admin.user.UsersAdminPageCommand;
import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * This is a helper class that is used to work with servlet commands.
 *
 * It handles mapping of the url paths to the commands.
 */
public class CommandManager {

    private static final Logger LOGGER = Logger.getLogger(CommandManager.class);

    private HashMap<String, ServletCommand> commands;
    private static String errorPage;

    public CommandManager(){
        LOGGER.info("Initializing CommandManager");

        commands = new HashMap<>();

        commands.put("/", new MainPageCommand());
        commands.put("/login", new LoginCommand());
        commands.put("/logout", new LogoutController());
        commands.put("/register", new RegisterCommand());
        commands.put("/magazine", new MagazinePageCommand());
        commands.put("/category", new CategoryPageCommand());
        commands.put("/admin/dashboard", new AdminPageCommand());
        commands.put("/subscribe", new SubscribePageCommand());
        commands.put("/account", new AccountPageCommand());

        //admin categories
        commands.put("/admin/categories", new CategoriesAdminPageCommand());
        commands.put("/admin/categories/add", new AddCategoryAdminPageCommand());
        commands.put("/admin/categories/delete", new DeleteCategoryAdminCommand());
        commands.put("/admin/categories/update", new UpdateCategoryAdminCommand());
        commands.put("/admin/categories/edit", new EditCategoryAdminPageCommand());

        //admin publishers
        commands.put("/admin/publishers", new PublishersAdminPageCommand());
        commands.put("/admin/publishers/edit", new EditPublisherAdminPageCommand());
        commands.put("/admin/publishers/add", new AddPublisherAdminPageCommand());
        commands.put("/admin/publishers/delete", new DeletePublisherAdminCommand());
        commands.put("/admin/publishers/update", new UpdatePublisherAdminCommand());

        //admin users
        commands.put("/admin/users", new UsersAdminPageCommand());
        commands.put("/admin/admins", new AdminsAdminPageCommand());

        //admin magazines
        commands.put("/admin/magazines", new MagazinesAdminPageCommand());
        commands.put("/admin/magazines/add", new AddMagazineAdminPageCommand());

        MappingProperties properties = MappingProperties.getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    /**
     * This method is used to get a command instance based on a request.
     *
     * @param request http request from servlet.
     * @return        A servlet command instance.
     */
    public ServletCommand getCommand(HttpServletRequest request) {
        String command = request.getRequestURI().substring(request.getContextPath().length());
        if(command.endsWith("/")) {
            command = command.substring(0, command.length() - 1);
        }
        LOGGER.info("Getting command " + command);

        if(commands.get(command) == null) {
            return commands.get("/");
        }

        return commands.get(command);
    }
}
