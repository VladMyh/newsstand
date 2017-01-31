package com.newsstand.servlet;

import com.newsstand.command.CommandManager;
import com.newsstand.command.ServletCommand;
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
		LOGGER.info("Initializing Servlet");
		commandManager = new CommandManager();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		LOGGER.info("Processing " + request.getMethod() + " request");

		ServletCommand command = commandManager.getCommand(request);
		String page = command.execute(request, response);
		request.getRequestDispatcher(page).forward(request, response);
	}
}
