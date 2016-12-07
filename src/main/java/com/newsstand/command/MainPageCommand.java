package com.newsstand.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPageCommand implements ServletCommand{
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "main.jsp";
    }
}
