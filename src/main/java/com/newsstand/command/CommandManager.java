package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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

        MappingProperties properties = MappingProperties.getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    public ServletCommand getCommand(HttpServletRequest request) {
        String command = request.getRequestURI().substring(request.getContextPath().length());
        LOGGER.info("Getting command " + command);

        if(commands.get(command) == null) {
            return commands.get("/");
        }

        return commands.get(command);
    }
}
