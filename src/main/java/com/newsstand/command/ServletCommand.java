package com.newsstand.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
