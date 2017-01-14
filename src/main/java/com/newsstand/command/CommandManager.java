package com.newsstand.command;

import com.newsstand.properties.MappingProperties;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class CommandManager {
    private static final Logger LOGGER = Logger.getLogger(CommandManager.class);

    private HashMap<String, ServletCommand> GetCommands;
    private HashMap<String, ServletCommand> PostCommands;
    private static String errorPage;

    public CommandManager(){
        GetCommands = new HashMap<>();
        PostCommands = new HashMap<>();

        GetCommands.put("", new MainPageCommand());
        GetCommands.put("/login", new LoginCommand());
        GetCommands.put("/register", new RegisterCommand());

        MappingProperties properties = MappingProperties.getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    public String doGetCommand(String command, HttpServletRequest request, HttpServletResponse response) {
        try {
            return GetCommands.get(command).execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorPage;
    }

    public String doPostCommand(String command, HttpServletRequest request, HttpServletResponse response) {
        try {
            return PostCommands.get(command).execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return errorPage;
    }
}
