package com.newsstand.servlets;

import com.newsstand.command.CommandManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(Servlet.class);

	private CommandManager commandManager;

	public void init(ServletConfig config) throws ServletException {
		commandManager = new CommandManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String command = request.getParameter("action");
		LOGGER.info("Processing get request with command: " + command);

		response.sendRedirect(commandManager.doGetCommand(command, request, response));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String command = request.getParameter("action");
		LOGGER.info("Processing post request with command: " + command);

		response.sendRedirect(commandManager.doPostCommand(command, request, response));
	}
}
